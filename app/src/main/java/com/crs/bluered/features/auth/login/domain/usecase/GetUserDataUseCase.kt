package com.crs.bluered.features.auth.login.domain.usecase

import com.crs.bluered.core.domain.model.UserData
import com.crs.bluered.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.take
import javax.inject.Inject

interface GetUserDataUseCase {
    suspend operator fun invoke(parameters: Unit = Unit): Flow<UserData>
}

class GetUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : GetUserDataUseCase {
    override suspend fun invoke(parameters: Unit): Flow<UserData> {
        return loginRepository.getData()
            .catch { error ->
                emit(
                    UserData(errorMessage = error.message ?: "Erro desconhecido")
                )
            }.take(1)
    }

}
