package com.crs.bluered.features.auth.login.domain.usecase

import com.crs.bluered.core.domain.exceptions.LoginException
import com.crs.bluered.features.auth.login.domain.repository.LoginRepository

interface SaveLocalStorageTokenUserData {
    suspend operator fun invoke(token: String, username: String)
}

class SaveLocalStorageTokenUserDataImpl constructor(
    private val loginRepository: LoginRepository,
) : SaveLocalStorageTokenUserData {
    override suspend fun invoke(token: String, username: String) {
        return try {
            loginRepository.saveData(
                token = token,
                username = username
            )
        } catch (e: Exception) {
            throw LoginException(message = "Erro ao salvar dados no local storage")
        }
    }

}