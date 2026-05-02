package com.crs.bluered.features.deck.edit.presentation

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
import com.crs.bluered.features.deck.create.presentation.components.CreateDeckForm
import com.crs.bluered.shared.domain.enums.DeckVisibility
import com.crs.bluered.ui.theme.BlueRedTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDeckScreen(
    modifier: Modifier = Modifier,
    uiState: EditDeckUIState,
    onEvent: (EditDeckEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "EDITAR DECK",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back_24),
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            EditDeckContainer(
                modifier = modifier,
                uiState = uiState,
                onEvent = onEvent,
                paddingValues = paddingValues
            )
        }
    )
}

@Composable
private fun EditDeckContainer(
    modifier: Modifier = Modifier,
    uiState: EditDeckUIState,
    onEvent: (EditDeckEvent) -> Unit,
    paddingValues: androidx.compose.foundation.layout.PaddingValues
) {
    CreateDeckForm(
        modifier = modifier,
        title = uiState.title,
        visibility = uiState.visibility,
        maxMembers = uiState.maxMembers,
        isLoading = uiState.isLoading,
        generalErrorMessage = uiState.generalErrorMessage,
        onTitleChange = { onEvent(EditDeckEvent.OnTitleChange(it)) },
        onVisibilityChange = { onEvent(EditDeckEvent.OnVisibilityChange(it)) },
        onMaxMembersChange = { onEvent(EditDeckEvent.OnMaxMembersChange(it)) },
        onSubmit = { onEvent(EditDeckEvent.OnSubmit) }
    )
}

@Preview(showBackground = true)
@Composable
private fun EditDeckScreenPreview() {
    BlueRedTheme {
        EditDeckScreen(
            uiState = EditDeckUIState(
                title = InputFieldState(value = "Deck Editado"),
                visibility = DeckVisibility.PUBLIC.apiValue,
                maxMembers = InputFieldState(value = "8"),
                isLoading = false,
                generalErrorMessage = null
            ),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}
