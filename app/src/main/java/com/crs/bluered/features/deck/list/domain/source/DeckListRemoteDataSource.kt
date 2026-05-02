package com.crs.bluered.features.deck.list.domain.source

import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.shared.domain.enums.DeckListScope

interface DeckListRemoteDataSource {

    suspend fun list(params: Params): ServiceResult<PaginatedResponse<DeckListItem>>
    data class Params(
        val page: Int = 1,
        val perPage: Int = 10,
        val scope: DeckListScope
    )
}