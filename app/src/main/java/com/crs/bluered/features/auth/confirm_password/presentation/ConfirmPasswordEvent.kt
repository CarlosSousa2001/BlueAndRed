package com.crs.bluered.features.auth.confirm_password.presentation

sealed interface ConfirmPasswordEvent {
    data class PasswordChanged(val value: String) : ConfirmPasswordEvent
    data class ConfirmPasswordChanged(val value: String) : ConfirmPasswordEvent
    data object TogglePasswordVisibility : ConfirmPasswordEvent
    data object ToggleConfirmPasswordVisibility : ConfirmPasswordEvent
    data object Submit : ConfirmPasswordEvent
}