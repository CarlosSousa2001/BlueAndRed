package com.crs.bluered.shared.domain.entities

import com.crs.bluered.shared.domain.enums.DeckVisibility

data class DeckResponseEntity(
    val id: String,
    val title: String,
    val ownerId: String,
    val maxMembers: Int?,
    val visibility: DeckVisibility,
    val isOfficial: Boolean,
    val isActive: Boolean,
    val createdAt: String
)