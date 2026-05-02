package com.crs.bluered.features.deck.list.data.model.dto
import kotlinx.serialization.Serializable

@Serializable
data class DeckListItemDto(
    val id: String,
    val title: String,
    val maxMembers: Int? = null,
    val ownerId: String,
    val visibility: String,
    val isOfficial: Boolean,
    val isActive: Boolean,
    val createdAt: String,
    val cardsCount: Int,
    val optionsCount: Int
)