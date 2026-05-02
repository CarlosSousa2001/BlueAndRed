package com.crs.bluered.core.session

import kotlinx.coroutines.flow.StateFlow

interface SessionManager {
    val state: StateFlow<SessionState>

    suspend fun onUnauthorized()

    fun onAuthenticated()
}