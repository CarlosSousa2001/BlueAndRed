package com.crs.bluered.shared.data.mappers

import com.crs.bluered.shared.data.dto.CardOptionsResponseEntityDto
import com.crs.bluered.shared.data.dto.CardResponseDto
import com.crs.bluered.shared.domain.entities.CardOptionsResponseEntity
import com.crs.bluered.shared.domain.entities.CardResponseEntity

fun CardOptionsResponseEntityDto.toDomainCardOptionsResponseEntityDto(): CardOptionsResponseEntity {
    return CardOptionsResponseEntity(
        id = id,
        cardId = cardId,
        label = label,
        text = text
    )
}

fun CardResponseDto.toDomain(): CardResponseEntity {
    return CardResponseEntity(
        id = id,
        deckId = deckId,
        deckTitle = deckTitle,
        createdAt = createdAt,
        updatedAt = updatedAt,
        options = options.map { it.toDomainCardOptionsResponseEntityDto() }
    )
}