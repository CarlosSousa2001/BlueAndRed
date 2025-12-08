package com.crs.bluered.core.di

import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.core.utils.DispatchersProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {
    @Binds
    fun provideDispatchersProvider(dispatchersProvider: DispatchersProviderImpl) : DispatchersProvider
}