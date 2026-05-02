package com.crs.bluered.features.card.create.di

import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.features.card.create.data.repository.CreateCardRepositoryImpl
import com.crs.bluered.features.card.create.data.source.CreateCardRemoteDataSourceImpl
import com.crs.bluered.features.card.create.domain.respository.CreateCardRepository
import com.crs.bluered.features.card.create.domain.source.CreateCardRemoteDataSource
import com.crs.bluered.features.card.create.domain.usecase.CreateCardUseCase
import com.crs.bluered.features.card.create.domain.usecase.CreateCardUseCaseImpl
import com.crs.bluered.features.card.create.domain.usecase.ValidateCreateDeckInputUseCase
import com.crs.bluered.features.card.create.domain.usecase.ValidateCreateDeckInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CreateCardModule {

    @Provides
    @Singleton
    fun provideValidateCreateCardInputUseCase(): ValidateCreateDeckInputUseCase {
        return ValidateCreateDeckInputUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideCreateCardRemoteDataSource(
        client: HttpClient
    ): CreateCardRemoteDataSource {
        return CreateCardRemoteDataSourceImpl(client = client)
    }

    @Provides
    @Singleton
    fun provideCreateCardRepository(
        remoteDataSource: CreateCardRemoteDataSource,
        dispatchersProvider: DispatchersProvider
    ): CreateCardRepository{
        return CreateCardRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    @Singleton
    fun provideCreateCardUseCase(
        repository: CreateCardRepository
    ): CreateCardUseCase {
        return CreateCardUseCaseImpl(
            repository = repository
        )

    }
}