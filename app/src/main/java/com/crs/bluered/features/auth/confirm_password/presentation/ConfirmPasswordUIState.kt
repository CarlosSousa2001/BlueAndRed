package com.crs.bluered.features.auth.confirm_password.presentation

import com.crs.bluered.core.domain.model.InputFieldState

data class ConfirmPasswordUIState(
    val password: InputFieldState = InputFieldState(),
    val confirmPassword: InputFieldState = InputFieldState(),
    val isPasswordShown: Boolean = false,
    val isConfirmPasswordShown: Boolean = false,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
)
