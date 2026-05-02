package com.crs.bluered.core.di

import com.crs.bluered.core.session.SessionManager
import com.crs.bluered.core.session.SessionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SessionModule {

    @Binds
    @Singleton
    fun bindSessionManager(impl: SessionManagerImpl): SessionManager
}