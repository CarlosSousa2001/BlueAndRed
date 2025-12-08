package com.crs.bluered.features.auth.login.domain.usecase

import com.crs.bluered.core.domain.exceptions.LoginException
import com.crs.bluered.features.auth.login.domain.model.AuthRequestModel
import com.crs.bluered.features.auth.login.domain.model.TokenResponseModel
import com.crs.bluered.features.auth.login.domain.repository.LoginRepository
import javax.inject.Inject

interface LoginUseCase {
    suspend operator fun invoke(authRequestModel: AuthRequestModel): TokenResponseModel
}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
) : LoginUseCase {
    override suspend fun invoke(authRequestModel: AuthRequestModel): TokenResponseModel {
        return try {
            loginRepository.login(authRequestModel)

        } catch (e: Exception) {
            throw LoginException(message = "Usuário ou senhas incorretos")
        }
    }
}