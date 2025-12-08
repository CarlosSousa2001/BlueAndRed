package com.crs.bluered.features.auth.login.presentation.components

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
import com.crs.bluered.features.auth.login.presentation.LoginEvent
import com.crs.bluered.features.auth.login.presentation.LoginUiState
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun LoginContainer(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEvent: (LoginEvent) -> Unit,
    paddingValues: PaddingValues,
    onNavigateToRegisterScreen: () -> Unit = {},
    onLoginWithGoogle: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(sizing.md)
        ) {

            Image(
                painter = painterResource(id = R.drawable.bg_login_img_foreground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f)
            )

            LoginForm(
                modifier = Modifier.fillMaxWidth(),
                email = uiState.email,
                password = uiState.password,
                isPasswordShown = uiState.isPasswordShown,
                isLoading = uiState.isLoading,
                generalErrorMessage = uiState.generalErrorMessage,
                onEmailChange = { onEvent(LoginEvent.EmailChanged(it)) },
                onPasswordChange = { onEvent(LoginEvent.PasswordChanged(it)) },
                onTogglePasswordVisibility = {
                    onEvent(LoginEvent.TogglePasswordVisibility)
                },
                onSubmit = { onEvent(LoginEvent.Submit) },
                onLoginWithGoogle = onLoginWithGoogle
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Não tem uma conta? ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Cadastre-se",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.clickable { onNavigateToRegisterScreen() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContainerPreview() {
    BlueRedTheme {
        LoginContainer(
            uiState = LoginUiState(),
            onEvent = {},
            paddingValues = PaddingValues(0.dp)
        )
    }
}
