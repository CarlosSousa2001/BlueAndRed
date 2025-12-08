package com.crs.bluered.features.auth.login.data.repository

import com.crs.bluered.core.data.local.datastore.DataStoreLocalDataSource
import com.crs.bluered.core.domain.model.UserData
import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.features.auth.login.domain.model.AuthRequestModel
import com.crs.bluered.features.auth.login.domain.model.TokenResponseModel
import com.crs.bluered.features.auth.login.domain.repository.LoginRepository
import com.crs.bluered.features.auth.login.domain.source.LoginRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: DataStoreLocalDataSource,
    private val dispatchersProvider: DispatchersProvider
) : LoginRepository {
    override suspend fun login(authRequestModel: AuthRequestModel): TokenResponseModel {
        return withContext(dispatchersProvider.io()) {
            loginRemoteDataSource.login(authRequestModel)
        }
    }

    override fun getData(): Flow<UserData> = loginLocalDataSource.getData()

    override suspend fun saveData(token: String, username: String) {
        return withContext(dispatchersProvider.io()) {
            loginLocalDataSource.saveData(token = token, username = username)
        }
    }

    override suspend fun clearAll() {
        return withContext(dispatchersProvider.io()) {
            loginLocalDataSource.clearAll()
        }
    }
}