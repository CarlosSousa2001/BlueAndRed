package com.crs.bluered.features.card.list.domain.usecase

import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.ResponseData
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.card.list.domain.repository.CardListRepository
import com.crs.bluered.shared.domain.entities.CardResponseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CardListUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<PaginatedResponse<CardResponseEntity>>>
    data class Parameters(
        val page: Int = 1,
        val perPage: Int = 10,
        val deckId: String
    )
}

class CardListUseCaseImpl @Inject constructor(
    private val repository: CardListRepository
) : CardListUseCase {
    override fun invoke(parameters: CardListUseCase.Parameters): Flow<ResponseData<PaginatedResponse<CardResponseEntity>>> {
        return flow {
            emit(ResponseData.Loading)

            val repoParams = CardListRepository.Params(
                page = parameters.page,
                perPage = parameters.perPage,
                deckId = parameters.deckId
            )

            when (val result = repository.list(params = repoParams)) {
                is ServiceResult.Success -> emit(ResponseData.Success(result.data))
                is ServiceResult.Error -> emit(
                    ResponseData.Error(Throwable(result.message ?: "Erro ao listar cards"))
                )
            }
        }
    }
}
