package com.crs.bluered.features.deck.create.data.repository
import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.deck.create.domain.model.CreateDeckModel
import com.crs.bluered.features.deck.create.domain.repository.CreateDeckRepository
import com.crs.bluered.features.deck.create.domain.source.CreateDeckRemoteDataSource
import com.crs.bluered.shared.domain.entities.DeckResponseEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateDeckRepositoryImpl @Inject constructor(
    private val remoteDataSource: CreateDeckRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
) : CreateDeckRepository {
    override suspend fun create(input: CreateDeckModel): ServiceResult<DeckResponseEntity> {
        return withContext(dispatchersProvider.io()) {
            remoteDataSource.create(input)
        }
    }
}