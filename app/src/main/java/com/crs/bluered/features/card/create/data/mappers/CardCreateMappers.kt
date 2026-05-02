package com.crs.bluered.features.card.create.data.mappers

import com.crs.bluered.features.card.create.data.dto.CardModelDto
import com.crs.bluered.features.card.create.data.dto.CardRequestDto
import com.crs.bluered.features.card.create.domain.model.CardModel
import com.crs.bluered.features.card.create.domain.model.CardRequest

fun CardModel.toDto(): CardModelDto = CardModelDto(label = label, text = text)

fun CardRequest.toDto(): CardRequestDto = CardRequestDto(
    options = options.map { it.toDto() }
)
