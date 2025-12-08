package com.crs.bluered.features.auth.validate_code.presentation

sealed interface ValidationCodeEvent {
    data class CodeChanged(val value: String) : ValidationCodeEvent
    data object Submit : ValidationCodeEvent
    data object ResendCode : ValidationCodeEvent
}