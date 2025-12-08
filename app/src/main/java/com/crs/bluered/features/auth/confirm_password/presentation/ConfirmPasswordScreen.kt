package com.crs.bluered.features.auth.confirm_password.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.features.auth.confirm_password.presentation.components.ConfirmPasswordContainer
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun ConfirmPasswordScreen(
    uiState: ConfirmPasswordUIState,
    onEvent: (ConfirmPasswordEvent) -> Unit,
    onNavigateBackToLogin: () -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            ConfirmPasswordContainer(
                uiState = uiState,
                onEvent = onEvent,
                paddingValues = paddingValues,
                onNavigateBackToLogin = onNavigateBackToLogin
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPasswordScreenPreview() {
    BlueRedTheme {
        ConfirmPasswordScreen(
            uiState = ConfirmPasswordUIState(),
            onEvent = {},
            onNavigateBackToLogin = {}
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ConfirmPasswordScreenPreviewDark() {
    BlueRedTheme {
        ConfirmPasswordScreen(
            uiState = ConfirmPasswordUIState(),
            onEvent = {},
            onNavigateBackToLogin = {}
        )
    }
}