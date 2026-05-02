package com.crs.bluered.shared.domain.entities

data class CardResponseEntity(
    val id: String,
    val deckId: String,
    val deckTitle: String?,
    val createdAt: String,
    val updatedAt: String,
    val options: List<CardOptionsResponseEntity>
)
