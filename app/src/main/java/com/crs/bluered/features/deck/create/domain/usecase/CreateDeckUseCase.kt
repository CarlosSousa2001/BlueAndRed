package com.crs.bluered.features.deck.create.domain.usecase

import com.crs.bluered.core.utils.ResponseData
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.deck.create.domain.model.CreateDeckModel
import com.crs.bluered.features.deck.create.domain.repository.CreateDeckRepository
import com.crs.bluered.shared.domain.entities.DeckResponseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CreateDeckUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<DeckResponseEntity>>
    data class Parameters(val input: CreateDeckModel)
}

class CreateDeckUseCaseImpl constructor(
    private val repository: CreateDeckRepository
) : CreateDeckUseCase {
    override fun invoke(parameters: CreateDeckUseCase.Parameters): Flow<ResponseData<DeckResponseEntity>> {
        return flow {
            emit(ResponseData.Loading)
            when (val result = repository.create(parameters.input)) {
                is ServiceResult.Success -> emit(ResponseData.Success(result.data))
                is ServiceResult.Error -> emit(
                    ResponseData.Error(
                        Throwable(result.message ?: "Erro ao criar deck")
                    )
                )
            }
        }
    }
}