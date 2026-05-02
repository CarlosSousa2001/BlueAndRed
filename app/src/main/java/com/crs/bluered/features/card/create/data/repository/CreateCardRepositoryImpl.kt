package com.crs.bluered.features.card.create.data.repository

import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.card.create.domain.model.CardRequest
import com.crs.bluered.features.card.create.domain.respository.CreateCardRepository
import com.crs.bluered.features.card.create.domain.source.CreateCardRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CreateCardRepositoryImpl @Inject constructor(
    private val remoteDataSource: CreateCardRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
): CreateCardRepository{
    override suspend fun create(cards: CardRequest, deckId:String): ServiceResult<Unit> {
      return withContext(dispatchersProvider.io()){
            remoteDataSource.create(cards, deckId)
        }
    }
}