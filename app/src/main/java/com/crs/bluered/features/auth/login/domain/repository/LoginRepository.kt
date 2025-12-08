package com.crs.bluered.features.auth.login.domain.repository

import com.crs.bluered.core.domain.model.UserData
import com.crs.bluered.features.auth.login.domain.model.AuthRequestModel
import com.crs.bluered.features.auth.login.domain.model.TokenResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(authRequestModel: AuthRequestModel) : TokenResponseModel

    fun getData(): Flow<UserData>

    suspend fun saveData(token: String, username: String)

    suspend fun clearAll()
}