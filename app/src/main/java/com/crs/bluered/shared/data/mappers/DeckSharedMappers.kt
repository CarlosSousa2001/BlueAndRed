package com.crs.bluered.shared.data.mappers

import com.crs.bluered.shared.data.dto.DeckResponseEntityDto
import com.crs.bluered.shared.domain.entities.DeckResponseEntity

fun DeckResponseEntity.toDeckResponseEntityDto(): DeckResponseEntityDto {
    return DeckResponseEntityDto(
        id = id,
        title = title,
        ownerId = ownerId,
        maxMembers = maxMembers,
        visibility = visibility,
        inviteCode = inviteCode,
        isOfficial = isOfficial,
        isActive = isActive,
        createdAt = createdAt
    )
}