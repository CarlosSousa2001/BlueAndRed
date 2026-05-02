package com.crs.bluered.features.card.list.domain.repository

import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.shared.domain.entities.CardResponseEntity

interface CardListRepository {

    suspend fun list(params: Params): ServiceResult<PaginatedResponse<CardResponseEntity>>

    data class Params(
        val page: Int = 1,
        val perPage: Int = 10,
        val deckId: String
    )

}