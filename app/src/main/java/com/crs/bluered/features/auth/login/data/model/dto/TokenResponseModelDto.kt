package com.crs.bluered.features.auth.login.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponseModelDto(
    val token : String? = null,
    val username : String? = null
)
