package com.crs.bluered

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.bluered.core.session.SessionManager
import com.crs.bluered.core.session.SessionState
import com.crs.bluered.core.utils.logging.logInfo
import com.crs.bluered.features.auth.login.domain.usecase.GetUserDataUseCase
import com.crs.bluered.ui.navigation.screens.Graphs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.invoke

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    val sessionState: StateFlow<SessionState> = sessionManager.state
    private val _isSplashLoading = MutableStateFlow(true)
    val isSplashLoading = _isSplashLoading.asStateFlow()

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getUserDataUseCase.invoke().collect { userData ->
                if (userData.token.isNotEmpty()) {
                    _uiState.update { it.copy(startDestination = Graphs.MainGraph) }
                    sessionManager.onAuthenticated()
                } else {
                    _uiState.update { it.copy(startDestination = Graphs.AuthGraph) }
                }

                if (!userData.errorMessage.isNullOrEmpty()) {
                    logInfo("USER_DATA", message = "UserData: ${userData.errorMessage}")
                }

                delay(1000)

                _isSplashLoading.update { false }
            }
        }
    }
}