package com.crs.bluered.features.deck.list.di

import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.features.deck.list.data.repository.DeckListRepositoryImpl
import com.crs.bluered.features.deck.list.data.source.DeckListRemoteDataSourceImpl
import com.crs.bluered.features.deck.list.domain.repository.DeckListRepository
import com.crs.bluered.features.deck.list.domain.source.DeckListRemoteDataSource
import com.crs.bluered.features.deck.list.domain.usecase.DeckListUseCase
import com.crs.bluered.features.deck.list.domain.usecase.DeckListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DeckListModule {

    @Provides
    @Singleton
    fun provideDeckListRemoteDataSource(
        client: HttpClient
    ): DeckListRemoteDataSource {
        return DeckListRemoteDataSourceImpl(
            client = client
        )
    }

    @Provides
    @Singleton
    fun provideDeckListRepository(
        remoteDataSource: DeckListRemoteDataSource,
        dispatchersProvider: DispatchersProvider
    ): DeckListRepository {
        return DeckListRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    @Singleton
    fun provideDeckListUseCase(
        repository: DeckListRepository
    ): DeckListUseCase {
        return DeckListUseCaseImpl(
            repository = repository
        )
    }

}