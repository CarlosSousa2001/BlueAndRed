package com.crs.bluered.features.deck.create.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.bluered.core.utils.extensions.observerState
import com.crs.bluered.core.utils.validation.FieldErrors
import com.crs.bluered.features.deck.create.domain.model.CreateDeckModel
import com.crs.bluered.features.deck.create.domain.usecase.CreateDeckUseCase
import com.crs.bluered.features.deck.create.domain.usecase.ValidateCreateDeckInputUseCase
import com.crs.bluered.shared.domain.enums.DeckVisibility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateDeckViewModel @Inject constructor(
    private val createDeckUseCase: CreateDeckUseCase,
    private val validateCreateDeckInputUseCase: ValidateCreateDeckInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateDeckUIState())
    val uiState: StateFlow<CreateDeckUIState> = _uiState.asStateFlow()

    fun onEvent(event: CreateDeckEvent) {
        when (event) {
            is CreateDeckEvent.OnTitleChange -> {
                _uiState.update { state ->
                    state.copy(
                        title = state.title.copy(
                            value = event.value,
                            errorMessage = null
                        ),
                        generalErrorMessage = null
                    )
                }
            }

            is CreateDeckEvent.OnVisibilityChange -> {
                _uiState.update { state ->
                    val visibility = event.value
                    val shouldClearMaxMembers =
                        visibility == DeckVisibility.PUBLIC.toString()

                    state.copy(
                        visibility = visibility,
                        maxMembers = if (shouldClearMaxMembers) {
                            state.maxMembers.copy(value = "", errorMessage = null)
                        } else {
                            state.maxMembers
                        },
                        generalErrorMessage = null
                    )
                }
            }

            is CreateDeckEvent.OnMaxMembersChange -> {
                _uiState.update { state ->
                    state.copy(
                        maxMembers = state.maxMembers.copy(
                            value = event.value,
                            errorMessage = null
                        ),
                        generalErrorMessage = null
                    )
                }
            }

            CreateDeckEvent.OnSubmit -> submit()
        }
    }

    private fun submit() {
        val titleValue = _uiState.value.title.value
        val visibilityValue = _uiState.value.visibility
        val maxMembersValue = _uiState.value.maxMembers.value

        val errors = validateCreateDeckInputUseCase(
            title = titleValue,
            visibility = visibilityValue,
            maxMembers = maxMembersValue
        )

        if (errors.isNotEmpty()) {
            applyError(errors)
            return
        }

        _uiState.update { it.copy(isLoading = true, isSuccess = false, generalErrorMessage = null) }

        val maxMembersInt = maxMembersValue.trim().takeIf { it.isNotEmpty() }?.toIntOrNull()

        viewModelScope.launch {
            createDeckUseCase.invoke(
                CreateDeckUseCase.Parameters(
                    input = CreateDeckModel(
                        title = titleValue.trim(),
                        visibility = visibilityValue,
                        maxMembers = maxMembersInt
                    )
                )
            ).observerState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true, generalErrorMessage = null) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                            generalErrorMessage = error.message ?: "Erro ao criar deck"
                        )
                    }
                },
                onSuccess = { _ ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            generalErrorMessage = null
                        )
                    }
                }
            )
        }
    }

    private fun applyError(errors: FieldErrors) {
        _uiState.update { state ->
            state.copy(
                title = state.title.copy(errorMessage = errors["title"]),
                maxMembers = state.maxMembers.copy(errorMessage = errors["maxMembers"]),
                generalErrorMessage = errors["visibility"] ?: state.generalErrorMessage
            )
        }
    }

    fun clearSuccess() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}
