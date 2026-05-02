package com.crs.bluered.features.card.create.domain.usecase

import com.crs.bluered.core.utils.ResponseData
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.card.create.domain.model.CardRequest
import com.crs.bluered.features.card.create.domain.respository.CreateCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CreateCardUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<Unit>>
    data class Parameters(val input: CardRequest, val deckId: String)
}

class CreateCardUseCaseImpl @Inject constructor(
    private val repository: CreateCardRepository
) : CreateCardUseCase {
    override fun invoke(parameters: CreateCardUseCase.Parameters): Flow<ResponseData<Unit>> {
        return flow {
            emit(ResponseData.Loading)
            when (val result = repository.create(parameters.input, parameters.deckId)) {
                is ServiceResult.Success -> emit(ResponseData.Success(result.data))
                is ServiceResult.Error -> emit(
                    ResponseData.Error(
                        Throwable(result.message ?: "Erro ao criar cartao")
                    )
                )
            }
        }
    }

}


