package com.crs.bluered.features.auth.register.domain.usecase

import com.crs.bluered.core.domain.exceptions.LoginException
import com.crs.bluered.features.auth.register.domain.model.RegisterRequestModel
import com.crs.bluered.features.auth.register.domain.repository.RegisterRepository
import javax.inject.Inject

interface RegisterUseCase {
    suspend operator fun invoke(registerRequestModel: RegisterRequestModel): Boolean
}

class RegisterUseCaseImpl @Inject constructor(
    private val registerRepository: RegisterRepository
) : RegisterUseCase {
    override suspend fun invoke(registerRequestModel: RegisterRequestModel): Boolean {
        return try {
            registerRepository.register(registerRequestModel = registerRequestModel)
        } catch (e: Exception) {
            throw LoginException(message = "Generico")
        }
    }

}
