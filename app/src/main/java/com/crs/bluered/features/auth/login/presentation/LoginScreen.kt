package com.crs.bluered.features.auth.login.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.features.auth.login.presentation.components.LoginContainer
import com.crs.bluered.ui.navigation.NavDestinationHelper
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEvent: (LoginEvent) -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToMainGraph: () -> Unit,
    onNavigateToForgotPasswordScreen: () -> Unit
) {

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isSuccess
        },
        destination = {
            onNavigateToMainGraph()
        }
    )

    Scaffold(
        content = { paddingValues ->
            LoginContainer(
                uiState = uiState,
                onEvent = onEvent,
                paddingValues = paddingValues,
                onNavigateToRegisterScreen = {
                    onNavigateToRegisterScreen()
                },
                onLoginWithGoogle = {}
            )
        }
    )
}

@Preview(
    showBackground = true,
    //uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LoginScreenPreview() {
    BlueRedTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onEvent = {},
            onNavigateToRegisterScreen = {},
            onNavigateToMainGraph = {},
            onNavigateToForgotPasswordScreen = {}
        )
    }
}