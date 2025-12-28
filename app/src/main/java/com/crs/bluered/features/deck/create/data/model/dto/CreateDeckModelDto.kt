package com.crs.bluered.features.deck.create.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateDeckModelDto(
    val title: String,
    val visibility: String,
    val maxMembers: Int?
)
