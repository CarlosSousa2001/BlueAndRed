package com.crs.bluered.features.deck.create.di

import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.features.deck.create.data.repository.CreateDeckRepositoryImpl
import com.crs.bluered.features.deck.create.data.source.CreateDeckRemoteDataSourceImpl
import com.crs.bluered.features.deck.create.domain.repository.CreateDeckRepository
import com.crs.bluered.features.deck.create.domain.source.CreateDeckRemoteDataSource
import com.crs.bluered.features.deck.create.domain.usecase.CreateDeckUseCase
import com.crs.bluered.features.deck.create.domain.usecase.CreateDeckUseCaseImpl
import com.crs.bluered.features.deck.create.domain.usecase.ValidateCreateDeckInputUseCase
import com.crs.bluered.features.deck.create.domain.usecase.ValidateCreateDeckInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CreateDeckModule {

    @Provides
    @Singleton
    fun provideValidateCreateDeckInputUseCase(): ValidateCreateDeckInputUseCase {
        return ValidateCreateDeckInputUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideCreateDeckRemoteDataSource(
        client: HttpClient
    ): CreateDeckRemoteDataSource {
        return CreateDeckRemoteDataSourceImpl(
            client = client
        )
    }

    @Provides
    @Singleton
    fun provideCreateDeckRepository(
        remoteDataSource: CreateDeckRemoteDataSource,
        dispatchersProvider: DispatchersProvider
    ): CreateDeckRepository {
        return CreateDeckRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    @Singleton
    fun provideCreateDeckUseCase(
        repository: CreateDeckRepository
    ): CreateDeckUseCase {
        return CreateDeckUseCaseImpl(
            repository = repository
        )
    }
}