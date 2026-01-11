package com.crs.bluered.shared.data.mappers
import com.crs.bluered.shared.data.dto.CardOptionsResponseEntityDto
import com.crs.bluered.shared.domain.entities.CardOptionsResponseEntity

fun CardOptionsResponseEntityDto.toDomainCardOptionsResponseEntityDto() : CardOptionsResponseEntity {
    return CardOptionsResponseEntity(
        id = id,
        cardId = cardId,
        label = label,
        text = text
    )
}