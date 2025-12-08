package com.crs.bluered.features.auth.register.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.crs.bluered.features.auth.login.presentation.LoginUiState
import com.crs.bluered.features.auth.register.presentation.RegisterEvent
import com.crs.bluered.features.auth.register.presentation.RegisterUIState
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun RegisterContainer(
    uiState: RegisterUIState,
    onEvent: (RegisterEvent) -> Unit,
    paddingValues: PaddingValues,
    onNavigationSignInScreen: () -> Unit = {},
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
                painter = painterResource(id = R.drawable.bg_signup_img_foreground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f)
            )

            RegisterForm(
                modifier = Modifier.fillMaxWidth(),
                username = uiState.username,
                email = uiState.email,
                password = uiState.password,
                isPasswordShown = uiState.isPasswordShown,
                isLoading = uiState.isLoading,
                generalErrorMessage = uiState.generalErrorMessage,
                onUsernameChange = {
                    onEvent(RegisterEvent.UsernameChanged(it))
                },
                onEmailChange = { onEvent(RegisterEvent.EmailChanged(it)) },
                onPasswordChange = { onEvent(RegisterEvent.PasswordChanged(it)) },
                onTogglePasswordVisibility = {
                    onEvent(RegisterEvent.TogglePasswordVisibility)
                },
                onSubmit = { onEvent(RegisterEvent.Submit) },
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Já tem uma conta ? ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Entrar",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.clickable { onNavigationSignInScreen() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContainerPreview() {
    BlueRedTheme{
        RegisterContainer(
            uiState = RegisterUIState(),
            onEvent = {},
            paddingValues = PaddingValues(0.dp)
        )
    }
}