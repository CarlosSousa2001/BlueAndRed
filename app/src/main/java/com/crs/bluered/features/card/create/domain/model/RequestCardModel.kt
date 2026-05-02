package com.crs.bluered.features.card.create.domain.model

data class CardModel(
    val label: String,
    val text: String
)

data class CardRequest(
    val options: List<CardModel> = emptyList()
)

