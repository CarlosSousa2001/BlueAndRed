package com.crs.bluered.features.card.list.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.crs.bluered.core.utils.extensions.observerState
import com.crs.bluered.features.card.list.domain.usecase.CardListUseCase
import com.crs.bluered.ui.navigation.screens.MainScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val cardListUseCase: CardListUseCase
) : ViewModel() {

    private val route = savedStateHandle.toRoute<MainScreens.CardScreen>()

    private val _uiState = MutableStateFlow(
        CardListUiState(
            deckId = route.deckId,
            deckTitle = route.deckTitle,
            canEdit = route.canEdit,
            visibility = route.visibility,
            maxMembers = route.maxMembers
        )
    )
    val uiState: StateFlow<CardListUiState> = _uiState.asStateFlow()

    private var hasLoadedOnce = false
    private var fetchJob: Job? = null
    private var lastRequestedPage: Int? = null

    fun onEvent(event: CardListEvent) {
        when (event) {
            CardListEvent.LoadIfNeeded -> loadIfNeeded()
            CardListEvent.Refresh -> refresh()
            CardListEvent.LoadMore -> loadMore()
        }
    }

    private fun loadIfNeeded() {
        if (hasLoadedOnce) return
        hasLoadedOnce = true
        fetchPage(page = 1, mode = FetchMode.Initial)
    }

    private fun refresh() {
        lastRequestedPage = null
        fetchPage(page = 1, mode = FetchMode.Refresh)
    }

    private fun loadMore() {
        val state = _uiState.value
        val nextPage = state.meta?.nextPage ?: return
        if (!state.canLoadMore) return
        if (lastRequestedPage == nextPage) return

        fetchPage(page = nextPage, mode = FetchMode.LoadMore)
    }

    private fun fetchPage(page: Int, mode: FetchMode) {
        lastRequestedPage = page

        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val state = _uiState.value

            cardListUseCase(
                CardListUseCase.Parameters(
                    page = page,
                    perPage = state.perPage,
                    deckId = state.deckId
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
                    if (mode == FetchMode.LoadMore) {
                        lastRequestedPage = null
                    }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingMore = false,
                            errorMessage = error.message ?: "Erro ao listar cards"
                        )
                    }
                },
                onEmpty = {
                    _uiState.update {
                        when (mode) {
                            FetchMode.LoadMore -> it.copy(isLoadingMore = false)
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
                        current.copy(
                            items = when (mode) {
                                FetchMode.LoadMore -> current.items + response.data
                                FetchMode.Initial, FetchMode.Refresh -> response.data
                            },
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

    private enum class FetchMode { Initial, Refresh, LoadMore }
}
