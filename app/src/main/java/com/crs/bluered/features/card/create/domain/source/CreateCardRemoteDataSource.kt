package com.crs.bluered.features.card.create.domain.source

import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.card.create.domain.model.CardRequest

interface CreateCardRemoteDataSource {
    suspend fun create(cards: CardRequest, deckId:String): ServiceResult<Unit>
}