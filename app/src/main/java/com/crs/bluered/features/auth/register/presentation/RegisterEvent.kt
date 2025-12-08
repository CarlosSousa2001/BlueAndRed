package com.crs.bluered.features.auth.register.presentation

sealed interface RegisterEvent {
    data class UsernameChanged(val value: String) : RegisterEvent
    data class EmailChanged(val value: String) : RegisterEvent
    data class PasswordChanged(val value: String) : RegisterEvent
    data object TogglePasswordVisibility : RegisterEvent
    data object Submit : RegisterEvent
}