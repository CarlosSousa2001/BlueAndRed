package com.crs.bluered.features.auth.register.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.features.auth.register.presentation.components.RegisterContainer
import com.crs.bluered.ui.navigation.NavDestinationHelper
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun RegisterScreen(
    uiState: RegisterUIState,
    onEvent: (RegisterEvent) -> Unit,
    onNavigationToLoginScreen: () -> Unit
) {

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isSuccess
        },
        destination = {
            onNavigationToLoginScreen()
        }
    )


    Scaffold(
        content = { paddingValues ->
            RegisterContainer(
                uiState = uiState,
                onEvent = onEvent,
                paddingValues = paddingValues,
                onNavigationSignInScreen = {
                    onNavigationToLoginScreen()
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    BlueRedTheme {
        RegisterScreen(
            uiState = RegisterUIState(),
            onEvent = {},
            onNavigationToLoginScreen = {}
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun RegisterScreenPreviewDarkMode() {
    BlueRedTheme {
        RegisterScreen(
            uiState = RegisterUIState(),
            onEvent = {},
            onNavigationToLoginScreen = {}
        )
    }
}