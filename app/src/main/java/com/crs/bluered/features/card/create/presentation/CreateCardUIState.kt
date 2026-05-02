package com.crs.bluered.features.card.create.presentation

import com.crs.bluered.core.domain.model.InputFieldState

data class CreateCardUIState(
    val label: InputFieldState = InputFieldState(),
    val text: InputFieldState = InputFieldState(),
    val isLoading: Boolean = false,
    val isSuccess : Boolean = false,
    val generalErrorMessage: String? = null
)
