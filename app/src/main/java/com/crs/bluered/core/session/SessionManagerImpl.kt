package com.crs.bluered.core.session

import com.crs.bluered.core.data.local.datastore.DataStoreLocalDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManagerImpl @Inject constructor(
    private val localDataSource: DataStoreLocalDataSource
) : SessionManager {

    private val _state = MutableStateFlow<SessionState>(SessionState.Authenticated)
    override val state: StateFlow<SessionState> = _state.asStateFlow()

    /**
     * Padrão: ao receber 401, limpa tudo e manda a UI voltar pro fluxo de auth.
     * Guard clause para não ficar limpando 50x se várias requisições voltarem 401 em sequência.
     */
    override suspend fun onUnauthorized() {
        if (_state.value is SessionState.Unauthorized) return

        localDataSource.clearAll()
        _state.value = SessionState.Unauthorized
    }

    override fun onAuthenticated() {
        _state.value = SessionState.Authenticated
    }
}