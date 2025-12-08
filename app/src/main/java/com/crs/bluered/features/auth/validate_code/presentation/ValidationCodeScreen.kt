package com.crs.bluered.features.auth.validate_code.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.ui.features.auth.validation_code.presentation.components.ValidationCodeContainer
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun ValidationCodeScreen(
    uiState: ValidationCodeUIState,
    onEvent: (ValidationCodeEvent) -> Unit,
    onNavigateBackToLogin: () -> Unit,
) {
    Scaffold(
        content = { paddingValues ->
            ValidationCodeContainer(
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
private fun ValidationCodeScreenPreview() {
    BlueRedTheme {
        ValidationCodeScreen(
            uiState = ValidationCodeUIState(),
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
private fun ValidationCodeScreenPreviewDarkMode() {
    BlueRedTheme {
        ValidationCodeScreen(
            uiState = ValidationCodeUIState(),
            onEvent = {},
            onNavigateBackToLogin = {}
        )
    }
}