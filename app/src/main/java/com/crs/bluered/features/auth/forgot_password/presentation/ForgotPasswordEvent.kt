package com.crs.bluered.features.auth.forgot_password.presentation

sealed interface ForgotPasswordEvent {
    data class OnEmailChanged(val value: String) : ForgotPasswordEvent
    data object Submit : ForgotPasswordEvent
}