package com.crs.bluered.shared.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CardOptionsResponseEntityDto(
    val id: String,
    val cardId: String,
    val label: String,
    val text: String
)
