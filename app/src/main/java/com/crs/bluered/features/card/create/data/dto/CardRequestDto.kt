package com.crs.bluered.features.card.create.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CardModelDto(
    val label: String,
    val text: String
)

@Serializable
data class CardRequestDto(
    val options: List<CardModelDto>
)
