package com.crs.bluered.features.deck.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.bluered.core.utils.extensions.observerState
import com.crs.bluered.features.deck.list.domain.usecase.DeckListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeckListViewModel @Inject constructor(
    private val deckListUseCase: DeckListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DeckListUiState())
    val uiState: StateFlow<DeckListUiState> = _uiState.asStateFlow()

    private var hasLoadedOnce = false
    private var fetchJob: Job? = null
    private var lastRequestedPage: Int? = null

    fun loadIfNeeded() {
        if (hasLoadedOnce) return
        hasLoadedOnce = true
        fetchInitial()
    }

    fun refresh() {
        fetchInitial(isRefresh = true)
    }

    fun loadMore() {
        val state = _uiState.value
        val nextPage = state.meta?.nextPage ?: return
        if (!state.canLoadMore) return
        if (lastRequestedPage == nextPage) return

        fetchPage(page = nextPage, mode = FetchMode.LoadMore)
    }

    fun changeVisibility(value: String?) {
        _uiState.update {
            it.copy(
                visibility = value,
                page = 1,
                meta = null,
                items = emptyList(),
                errorMessage = null
            )
        }
        lastRequestedPage = null
        hasLoadedOnce = true
        fetchInitial()
    }

    private fun fetchInitial(isRefresh: Boolean = false) {
        fetchPage(page = 1, mode = if (isRefresh) FetchMode.Refresh else FetchMode.Initial)
    }

    private fun fetchPage(page: Int, mode: FetchMode) {
        lastRequestedPage = page

        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val state = _uiState.value

            deckListUseCase(
                DeckListUseCase.Parameters(
                    page = page,
                    perPage = state.perPage,
                    visibility = state.visibility,
                    isOfficial = state.isOfficial
                )
            ).observerState(
                onLoading = {
                    _uiState.update {
                        when (mode) {
                            FetchMode.LoadMore -> it.copy(
                                isLoadingMore = true,
                                errorMessage = null
                            )

                            FetchMode.Initial, FetchMode.Refresh -> it.copy(
                                isLoading = true,
                                isLoadingMore = false,
                                errorMessage = null,
                                items = if (mode == FetchMode.Initial) emptyList() else it.items,
                                meta = if (mode == FetchMode.Initial) null else it.meta,
                                page = 1
                            )
                        }
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            errorMessage = error.message ?: "Erro ao listar decks"
                        )
                    }
                },
                onEmpty = {
                    // para listagem paginada, "empty" normalmente significa:
                    // - initial: não tem itens
                    // - loadMore: não veio mais nada
                    _uiState.update {
                        when (mode) {
                            FetchMode.LoadMore -> it.copy(
                                isLoadingMore = false
                            )

                            FetchMode.Initial, FetchMode.Refresh -> it.copy(
                                isLoading = false,
                                isLoadingMore = false,
                                items = emptyList()
                            )
                        }
                    }
                },
                onSuccess = { response ->
                    _uiState.update { current ->
                        val mergedItems = when (mode) {
                            FetchMode.LoadMore -> current.items + response.data
                            FetchMode.Initial, FetchMode.Refresh -> response.data
                        }

                        current.copy(
                            items = mergedItems,
                            meta = response.meta,
                            page = response.meta.currentPage,
                            isLoading = false,
                            isLoadingMore = false,
                            errorMessage = null
                        )
                    }
                }
            )
        }
    }

    private enum class FetchMode {
        Initial,
        Refresh,
        LoadMore
    }

}