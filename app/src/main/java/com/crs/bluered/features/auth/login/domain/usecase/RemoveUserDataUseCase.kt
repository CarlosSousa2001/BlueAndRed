package com.crs.bluered.features.auth.login.domain.usecase

import com.crs.bluered.core.utils.ResponseData
import com.crs.bluered.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RemoveUserDataUseCase {
    suspend operator fun invoke(parameters: Unit = Unit): Flow<ResponseData<Unit>>
}

class RemoveUserDataUseCaseImpl @Inject constructor(
    val loginRepository: LoginRepository
):RemoveUserDataUseCase {
    override suspend fun invoke(parameters: Unit): Flow<ResponseData<Unit>> {
        return flow {
            try {
                emit(ResponseData.Success(loginRepository.clearAll()))
            } catch (e: Throwable) {
                emit(ResponseData.Error(e))
            }
        }
    }
}