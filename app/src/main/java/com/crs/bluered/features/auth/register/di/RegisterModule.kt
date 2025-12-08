package com.crs.bluered.features.auth.register.di

import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.features.auth.register.data.repository.RegisterRepositoryImpl
import com.crs.bluered.features.auth.register.data.source.RegisterRemoteDataSourceImpl
import com.crs.bluered.features.auth.register.domain.repository.RegisterRepository
import com.crs.bluered.features.auth.register.domain.source.RegisterRemoteDataSource
import com.crs.bluered.features.auth.register.domain.usecase.RegisterUseCase
import com.crs.bluered.features.auth.register.domain.usecase.RegisterUseCaseImpl
import com.crs.bluered.features.auth.register.domain.usecase.ValidateRegisterInputUseCase
import com.crs.bluered.features.auth.register.domain.usecase.ValidateRegisterInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {

    @Provides
    fun provideRegisterRemoteDataSource(
        httpClient: HttpClient
    ): RegisterRemoteDataSource {
        return RegisterRemoteDataSourceImpl(client = httpClient)
    }

    @Provides
    fun providerRegisterRepository(
        registerRemoteDataSource: RegisterRemoteDataSource,
        dispatchersProvider: DispatchersProvider
    ): RegisterRepository {
        return RegisterRepositoryImpl(
            registerRemoteDataSource = registerRemoteDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    fun providerRegisterUseCase(
        registerRepository: RegisterRepository
    ) : RegisterUseCase {
        return RegisterUseCaseImpl(registerRepository = registerRepository)
    }

    @Provides
    fun provideValidateRegisterUseCase() : ValidateRegisterInputUseCase {
        return ValidateRegisterInputUseCaseImpl()
    }
}