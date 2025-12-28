package com.crs.bluered.features.auth.login.presentation
import com.crs.bluered.core.domain.model.InputFieldState

data class LoginUiState(
    val email: InputFieldState = InputFieldState(),
    val password: InputFieldState = InputFieldState(),
    val isPasswordShown : Boolean = false,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
    val isSuccess : Boolean = false
)
