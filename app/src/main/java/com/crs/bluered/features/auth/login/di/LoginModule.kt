package com.crs.bluered.features.auth.login.di

import com.crs.bluered.core.data.local.datastore.DataStoreLocalDataSource
import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.features.auth.login.data.repository.LoginRepositoryImpl
import com.crs.bluered.features.auth.login.data.source.LoginRemoteDataSourceImpl
import com.crs.bluered.features.auth.login.domain.repository.LoginRepository
import com.crs.bluered.features.auth.login.domain.source.LoginRemoteDataSource
import com.crs.bluered.features.auth.login.domain.usecase.GetUserDataUseCase
import com.crs.bluered.features.auth.login.domain.usecase.GetUserDataUseCaseImpl
import com.crs.bluered.features.auth.login.domain.usecase.LoginUseCase
import com.crs.bluered.features.auth.login.domain.usecase.LoginUseCaseImpl
import com.crs.bluered.features.auth.login.domain.usecase.RemoveUserDataUseCase
import com.crs.bluered.features.auth.login.domain.usecase.RemoveUserDataUseCaseImpl
import com.crs.bluered.features.auth.login.domain.usecase.SaveLocalStorageTokenUserData
import com.crs.bluered.features.auth.login.domain.usecase.SaveLocalStorageTokenUserDataImpl
import com.crs.bluered.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import com.crs.bluered.features.auth.login.domain.usecase.ValidateLoginInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun providesValidateLoginUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCaseImpl()
    }

    @Provides
    fun provideLoginRemoteDataSource(
        client: HttpClient
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(client = client)
    }

    @Provides
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource,
        loginLocalDataSource: DataStoreLocalDataSource,
        dispatchersProvider: DispatchersProvider
    ): LoginRepository {
        return LoginRepositoryImpl(
            loginRemoteDataSource = loginRemoteDataSource,
            loginLocalDataSource = loginLocalDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    fun provideLoginUseCase(
        loginRepository: LoginRepository
    ): LoginUseCase {
        return LoginUseCaseImpl(loginRepository = loginRepository)
    }

    @Provides
    fun provideSaveLocalStorageTokenUserData(
        loginRepository: LoginRepository
    ): SaveLocalStorageTokenUserData {
        return SaveLocalStorageTokenUserDataImpl(loginRepository)
    }

    @Provides
    fun provideGetUserDataCase(repository: LoginRepository): GetUserDataUseCase {
        return GetUserDataUseCaseImpl(repository)
    }

    @Provides
    fun provideRemoveUserDataUseCase(
        repository: LoginRepository,
    ): RemoveUserDataUseCase {
        return RemoveUserDataUseCaseImpl(
            loginRepository = repository
        )
    }

}