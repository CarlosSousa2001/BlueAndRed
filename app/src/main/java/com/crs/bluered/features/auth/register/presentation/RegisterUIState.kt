package com.crs.bluered.features.auth.register.presentation

import com.crs.bluered.core.domain.model.InputFieldState

data class RegisterUIState(
    val username: InputFieldState = InputFieldState(),
    val email: InputFieldState = InputFieldState(),
    val password: InputFieldState = InputFieldState(),
    val isPasswordShown: Boolean = false,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isSuccess: Boolean = false
)
