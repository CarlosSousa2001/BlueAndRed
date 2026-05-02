package com.crs.bluered.features.deck.edit.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.crs.bluered.core.domain.model.InputFieldState
import com.crs.bluered.core.utils.validation.FieldErrors
import com.crs.bluered.features.deck.create.domain.usecase.ValidateCreateDeckInputUseCase
import com.crs.bluered.shared.domain.enums.DeckVisibility
import com.crs.bluered.ui.navigation.screens.MainScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditDeckViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val validateCreateDeckInputUseCase: ValidateCreateDeckInputUseCase
) : ViewModel() {

    private val route = savedStateHandle.toRoute<MainScreens.EditDeckScreen>()

    private val _uiState = MutableStateFlow(
        EditDeckUIState(
            deckId = route.deckId,
            deckTitle = route.deckTitle,
            title = InputFieldState(value = route.deckTitle),
            visibility = route.visibility,
            maxMembers = InputFieldState(value = route.maxMembers?.toString().orEmpty())
        )
    )
    val uiState: StateFlow<EditDeckUIState> = _uiState.asStateFlow()

    fun onEvent(event: EditDeckEvent) {
        when (event) {
            is EditDeckEvent.OnTitleChange -> {
                _uiState.update { state ->
                    state.copy(
                        title = state.title.copy(value = event.value, errorMessage = null),
                        generalErrorMessage = null
                    )
                }
            }

            is EditDeckEvent.OnVisibilityChange -> {
                _uiState.update { state ->
                    val shouldClearMaxMembers = event.value == DeckVisibility.PUBLIC.apiValue
                    state.copy(
                        visibility = event.value,
                        maxMembers = if (shouldClearMaxMembers) {
                            state.maxMembers.copy(value = "", errorMessage = null)
                        } else {
                            state.maxMembers.copy(errorMessage = null)
                        },
                        generalErrorMessage = null
                    )
                }
            }

            is EditDeckEvent.OnMaxMembersChange -> {
                _uiState.update { state ->
                    state.copy(
                        maxMembers = state.maxMembers.copy(value = event.value, errorMessage = null),
                        generalErrorMessage = null
                    )
                }
            }

            EditDeckEvent.OnSubmit -> submit()
        }
    }

    private fun submit() {
        val state = _uiState.value
        val errors = validateCreateDeckInputUseCase(
            title = state.title.value,
            visibility = state.visibility,
            maxMembers = state.maxMembers.value
        )

        if (errors.isNotEmpty()) {
            applyErrors(errors)
            return
        }

        _uiState.update {
            it.copy(
                generalErrorMessage = "Integração de edição ainda não foi conectada ao backend."
            )
        }
    }

    private fun applyErrors(errors: FieldErrors) {
        _uiState.update { state ->
            state.copy(
                title = state.title.copy(errorMessage = errors["title"]),
                maxMembers = state.maxMembers.copy(errorMessage = errors["maxMembers"]),
                generalErrorMessage = errors["visibility"] ?: state.generalErrorMessage
            )
        }
    }
}
