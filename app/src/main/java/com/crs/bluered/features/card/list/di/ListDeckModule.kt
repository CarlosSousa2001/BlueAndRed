package com.crs.bluered.features.card.list.di

import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.features.card.list.data.repository.CardListRepositoryImpl
import com.crs.bluered.features.card.list.data.source.CardListRemoteDataSourceImpl
import com.crs.bluered.features.card.list.domain.repository.CardListRepository
import com.crs.bluered.features.card.list.domain.source.CardListRemoteDataSource
import com.crs.bluered.features.card.list.domain.usecase.CardListUseCase
import com.crs.bluered.features.card.list.domain.usecase.CardListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListDeckModule {

    @Provides
    @Singleton
    fun provideCardListOptionsRemoteDataSource(
        client: HttpClient
    ): CardListRemoteDataSource {
        return CardListRemoteDataSourceImpl(
            client = client
        )
    }

    @Provides
    @Singleton
    fun provideCardListOptionsRepository(
        remoteDataSource: CardListRemoteDataSource,
        dispatchersProvider: DispatchersProvider
    ): CardListRepository {
        return CardListRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatchersProvider = dispatchersProvider
        )
    }

    @Provides
    @Singleton
    fun provideCardListUseCase(
        repository: CardListRepository
    ): CardListUseCase {
        return CardListUseCaseImpl(
            repository = repository
        )
    }
}