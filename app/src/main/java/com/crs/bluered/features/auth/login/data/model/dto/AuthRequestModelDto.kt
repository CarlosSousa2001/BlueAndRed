package com.crs.bluered.features.auth.login.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestModelDto(
    val email: String,
    val password: String
)
