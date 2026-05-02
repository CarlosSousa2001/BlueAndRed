package com.crs.bluered.features.deck.create.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.crs.bluered.R
import com.crs.bluered.core.domain.model.InputFieldState
import com.crs.bluered.features.deck.create.presentation.components.CreateDeckContainer
import com.crs.bluered.shared.domain.enums.DeckVisibility
import com.crs.bluered.ui.theme.BlueRedTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateDeckScreen(
    modifier: Modifier = Modifier,
    uiState: CreateDeckUIState,
    onEvent: (CreateDeckEvent) -> Unit,
    onNavigationToMainListScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "NOVO DECK".uppercase(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigationToMainListScreen) {
                        Icon(
                            painter  = painterResource(R.drawable.ic_arrow_back_24),
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            CreateDeckContainer(
                modifier = modifier,
                uiState = uiState,
                onEvent = onEvent,
                paddingValues = paddingValues
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CreateDeckScreenPreview() {
    BlueRedTheme {
        CreateDeckScreen(
            uiState = CreateDeckUIState(
                title = InputFieldState(value = "Meu Deck Incrível"),
                visibility = DeckVisibility.PUBLIC.apiValue,
                maxMembers = InputFieldState(value = "10"),
                isLoading = false,
                isSuccess = false,
                generalErrorMessage = null
            ),
            onEvent = {},
            onNavigationToMainListScreen = {}
        )
    }
}