package com.crs.bluered.features.auth.forgot_password.presentation

import com.crs.bluered.core.domain.model.InputFieldState

data class ForgotPasswordUIState(
    val email: InputFieldState = InputFieldState(),
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
)
