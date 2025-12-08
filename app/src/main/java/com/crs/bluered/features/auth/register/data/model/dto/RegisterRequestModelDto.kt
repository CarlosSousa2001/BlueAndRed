package com.crs.bluered.features.auth.register.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestModelDto(
    val username: String,
    val email: String,
    val password: String
)
