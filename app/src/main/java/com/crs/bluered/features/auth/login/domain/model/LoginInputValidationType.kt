package com.crs.bluered.features.auth.login.domain.model

enum class LoginInputValidationType {
    Valid,
    EmptyField,
    NoEmail,
    PasswordTooWeak,
    InvalidInput
}