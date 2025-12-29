package com.crs.bluered.features.deck.list.data.repository

import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.features.deck.list.domain.repository.DeckListRepository
import com.crs.bluered.features.deck.list.domain.source.DeckListRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeckListRepositoryImpl @Inject constructor(
    private val remoteDataSource: DeckListRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
) : DeckListRepository {
    override suspend fun list(params: DeckListRepository.Params): ServiceResult<PaginatedResponse<DeckListItem>> {
        return withContext(dispatchersProvider.io()) {
            remoteDataSource.list(
                params = DeckListRemoteDataSource.Params(
                    page = params.page,
                    perPage = params.perPage,
                    scope = params.scope
                )
            )
        }
    }
}