package com.crs.bluered.features.auth.confirm_password.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.bluered.R
import com.crs.bluered.features.auth.confirm_password.presentation.ConfirmPasswordEvent
import com.crs.bluered.features.auth.confirm_password.presentation.ConfirmPasswordUIState
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun ConfirmPasswordContainer(
    uiState: ConfirmPasswordUIState,
    onEvent: (ConfirmPasswordEvent) -> Unit,
    paddingValues: PaddingValues,
    onNavigateBackToLogin: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(sizing.sm)
        ) {

            Image(
                painter = painterResource(id = R.drawable.bg_reset_password_img_foreground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f)
            )

            ConfirmPasswordForm(
                modifier = Modifier.fillMaxWidth(),
                password = uiState.password,
                confirmPassword = uiState.confirmPassword,
                isPasswordShown = uiState.isPasswordShown,
                isConfirmPasswordShown = uiState.isConfirmPasswordShown,
                isLoading = uiState.isLoading,
                generalErrorMessage = uiState.generalErrorMessage,

                onPasswordChange = { onEvent(ConfirmPasswordEvent.PasswordChanged(it)) },
                onConfirmPasswordChange = { onEvent(ConfirmPasswordEvent.ConfirmPasswordChanged(it)) },
                onTogglePasswordVisibility = { onEvent(ConfirmPasswordEvent.TogglePasswordVisibility) },
                onToggleConfirmPasswordVisibility = { onEvent(ConfirmPasswordEvent.ToggleConfirmPasswordVisibility) },
                onSubmit = { onEvent(ConfirmPasswordEvent.Submit) }
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Lembrou da senha?",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Voltar para login",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.clickable { onNavigateBackToLogin() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPasswordContainerPreview() {
    BlueRedTheme {
        ConfirmPasswordContainer(
            uiState = ConfirmPasswordUIState(),
            onEvent = {},
            paddingValues = PaddingValues(0.dp)
        )
    }
}