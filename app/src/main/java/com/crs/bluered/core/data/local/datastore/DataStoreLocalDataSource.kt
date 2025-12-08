package com.crs.bluered.core.data.local.datastore
import com.crs.bluered.core.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface DataStoreLocalDataSource {
    fun getData(): Flow<UserData>
    suspend fun saveData(token: String, username: String)
    suspend fun clearAll()
}