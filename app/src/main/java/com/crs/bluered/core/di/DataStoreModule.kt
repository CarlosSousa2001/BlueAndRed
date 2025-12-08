package com.crs.bluered.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.crs.bluered.core.data.local.datastore.DataStoreLocalDataSource
import com.crs.bluered.core.data.local.datastore.DataStoreLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreData(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = {
            context.preferencesDataStoreFile("bluered_prefs")
        }
    )

    @Provides
    @Singleton
    fun provideDataStoreLocalDataSource(dataStorePreferences: DataStore<Preferences>): DataStoreLocalDataSource {
        return DataStoreLocalDataSourceImpl(dataStorePreferences)
    }

}