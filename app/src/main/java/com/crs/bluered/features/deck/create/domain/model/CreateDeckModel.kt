package com.crs.bluered.features.deck.create.domain.model

data class CreateDeckModel(
    val title: String,
    val visibility: String,
    val maxMembers: Int?
)