package com.crs.bluered.features.deck.list.data.model.mappers

import com.crs.bluered.features.deck.list.data.model.dto.DeckListItemDto
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.shared.domain.enums.DeckVisibility

fun DeckListItemDto.toDomain(): DeckListItem {
    return DeckListItem(
        id = id,
        title = title,
        maxMembers = maxMembers,
        ownerId = ownerId,
        visibility = DeckVisibility.valueOf(visibility),
        isOfficial = isOfficial,
        isActive = isActive,
        createdAt = createdAt,
        cardsCount = cardsCount,
        optionsCount = optionsCount
    )
}