package com.crs.bluered.features.deck.list.domain.model

import com.crs.bluered.shared.domain.enums.DeckVisibility

data class DeckListItem(
    val id: String,
    val title: String,
    val maxMembers: Int?,
    val ownerId: String,
    val visibility: DeckVisibility,
    val isOfficial: Boolean,
    val isActive: Boolean,
    val createdAt: String,
    val cardsCount: Int,
    val optionsCount: Int
)
