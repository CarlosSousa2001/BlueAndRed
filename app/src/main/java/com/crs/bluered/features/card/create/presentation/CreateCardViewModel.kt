package com.crs.bluered.features.card.create.presentation

import com.crs.bluered.core.utils.validation.FieldErrors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateCardViewModel {

    private val _uiState = MutableStateFlow(CreateCardUIState())
    val uiState: StateFlow<CreateCardUIState> = _uiState.asStateFlow()

    fun onEvent(event: CreateCardEvent){}

    fun onSubmit(){}

    private fun applyError(errors: FieldErrors) {

    }
}