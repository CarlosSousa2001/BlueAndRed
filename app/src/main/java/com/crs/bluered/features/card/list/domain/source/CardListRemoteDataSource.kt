package com.crs.bluered.features.card.list.domain.source

import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.shared.domain.entities.CardOptionsResponseEntity

interface CardListRemoteDataSource {

    suspend fun list(params: Params): ServiceResult<PaginatedResponse<CardOptionsResponseEntity>>
    data class Params(
        val page: Int = 1,
        val perPage: Int = 10,
        val deckId: String
    )
}