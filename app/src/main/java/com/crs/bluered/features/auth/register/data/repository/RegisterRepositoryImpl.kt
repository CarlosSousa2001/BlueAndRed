package com.crs.bluered.features.auth.register.data.repository

import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.features.auth.register.domain.model.RegisterRequestModel
import com.crs.bluered.features.auth.register.domain.repository.RegisterRepository
import com.crs.bluered.features.auth.register.domain.source.RegisterRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerRemoteDataSource : RegisterRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
): RegisterRepository {
    override suspend fun register(registerRequestModel: RegisterRequestModel) : Boolean {
        return withContext(dispatchersProvider.io()){
            registerRemoteDataSource.register(registerRequestModel)
        }
    }
}