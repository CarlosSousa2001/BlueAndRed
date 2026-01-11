package com.crs.bluered.features.card.list.data.repository

import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.card.list.domain.repository.CardListRepository
import com.crs.bluered.features.card.list.domain.source.CardListRemoteDataSource
import com.crs.bluered.shared.domain.entities.CardOptionsResponseEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardListRepositoryImpl @Inject constructor(
    private val remoteDataSource: CardListRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
) : CardListRepository {
    override suspend fun list(params: CardListRepository.Params): ServiceResult<PaginatedResponse<CardOptionsResponseEntity>> {
        return withContext(dispatchersProvider.io()) {
            remoteDataSource.list(
                params = CardListRemoteDataSource.Params(
                    page = params.page,
                    perPage = params.perPage,
                    deckId = params.deckId
                )
            )
        }
    }
}