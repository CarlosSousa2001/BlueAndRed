package com.crs.bluered.features.deck.create.data.model.mappers

import com.crs.bluered.features.deck.create.data.model.dto.CreateDeckModelDto
import com.crs.bluered.features.deck.create.domain.model.CreateDeckModel

fun CreateDeckModel.toCreateDeckModelDto(): CreateDeckModelDto {
    return CreateDeckModelDto(
        title = title,
        visibility = visibility,
        maxMembers = maxMembers
    )
}