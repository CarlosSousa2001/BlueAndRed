package com.crs.bluered.shared.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CardResponseDto(
    val id: String,
    val deckId: String,
    val deckTitle: String? = null,
    val createdAt: String,
    val updatedAt: String,
    val options: List<CardOptionsResponseEntityDto>
)
