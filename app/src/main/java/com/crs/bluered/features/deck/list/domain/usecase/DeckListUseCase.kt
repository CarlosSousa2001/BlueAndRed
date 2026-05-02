package com.crs.bluered.features.deck.list.domain.usecase

import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.ResponseData
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.features.deck.list.domain.repository.DeckListRepository
import com.crs.bluered.shared.domain.enums.DeckListScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface DeckListUseCase {
    operator fun invoke(parameters: DeckListUseCase.Parameters): Flow<ResponseData<PaginatedResponse<DeckListItem>>>
    data class Parameters(
        val page: Int = 1,
        val perPage: Int = 10,
        val scope: DeckListScope = DeckListScope.GLOBAL
    )
}

class DeckListUseCaseImpl @Inject constructor(
    private val repository: DeckListRepository
) : DeckListUseCase {
    override fun invoke(parameters: DeckListUseCase.Parameters): Flow<ResponseData<PaginatedResponse<DeckListItem>>> {
        return flow {
            emit(ResponseData.Loading)

            val repoParams = DeckListRepository.Params(
                page = parameters.page,
                perPage = parameters.perPage,
                scope = parameters.scope
            )

            when (val result = repository.list(params = repoParams)) {
                is ServiceResult.Success -> {
                    emit(ResponseData.Success(result.data))
                }

                is ServiceResult.Error -> {
                    emit(
                        ResponseData.Error(
                            Throwable(result.message ?: "Erro ao listar decks")
                        )
                    )
                }
            }
        }
    }
}