package com.crs.bluered.features.auth.validate_code.presentation

import com.crs.bluered.core.domain.model.InputFieldState

data class ValidationCodeUIState(
    val code: InputFieldState = InputFieldState(),
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
)
