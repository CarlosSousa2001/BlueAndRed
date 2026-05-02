package com.crs.bluered.shared.data.dto

import com.crs.bluered.shared.domain.enums.DeckVisibility
import kotlinx.serialization.Serializable

@Serializable
data class DeckResponseEntityDto(
    val id: String,
    val title: String,
    val ownerId: String,
    val maxMembers: Int?,
    val visibility: String,
    val inviteCode: String,
    val isOfficial: Boolean,
    val isActive: Boolean,
    val createdAt: String
)
