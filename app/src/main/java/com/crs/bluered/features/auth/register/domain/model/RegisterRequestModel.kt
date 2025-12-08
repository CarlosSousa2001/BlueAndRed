package com.crs.bluered.features.auth.register.domain.model

data class RegisterRequestModel(
    val username: String,
    val email: String,
    val password: String
)
