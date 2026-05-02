package com.crs.bluered.features.deck.create.domain.source
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.deck.create.domain.model.CreateDeckModel
import com.crs.bluered.shared.domain.entities.DeckResponseEntity

interface CreateDeckRemoteDataSource {
    suspend fun create(input: CreateDeckModel): ServiceResult<DeckResponseEntity>
}