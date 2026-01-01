package com.crs.bluered.shared.data.mappers

import com.crs.bluered.shared.data.dto.DeckResponseEntityDto
import com.crs.bluered.shared.domain.entities.DeckResponseEntity
import com.crs.bluered.shared.domain.enums.DeckVisibility

fun DeckResponseEntity.toDeckResponseEntityDto(): DeckResponseEntityDto {
    return DeckResponseEntityDto(
        id = id,
        title = title,
        ownerId = ownerId,
        maxMembers = maxMembers,
        visibility = visibility.toString(),
        inviteCode = inviteCode,
        isOfficial = isOfficial,
        isActive = isActive,
        createdAt = createdAt
    )
}

fun DeckResponseEntityDto.toDomainDeckResponseEntity(): DeckResponseEntity {
    return DeckResponseEntity(
        id = id,
        title = title,
        ownerId = ownerId,
        maxMembers = maxMembers,
        visibility = DeckVisibility.fromApiValue(visibility),
        inviteCode = inviteCode,
        isOfficial = isOfficial,
        isActive = isActive,
        createdAt = createdAt
    )
}