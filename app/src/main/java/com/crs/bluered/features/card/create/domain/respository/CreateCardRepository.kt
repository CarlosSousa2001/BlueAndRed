package com.crs.bluered.features.card.create.domain.respository

import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.card.create.domain.model.CardRequest

interface CreateCardRepository {
    suspend fun create(cards: CardRequest, deckId:String): ServiceResult<Unit>
}