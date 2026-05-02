package com.crs.bluered.core.session

sealed interface SessionState {
    data object Authenticated : SessionState
    data object Unauthorized : SessionState
}