package com.crs.bluered.features.deck.create.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.crs.bluered.features.deck.create.presentation.CreateDeckEvent
import com.crs.bluered.features.deck.create.presentation.CreateDeckUIState

@Composable
fun CreateDeckContainer(
    modifier: Modifier = Modifier,
    uiState: CreateDeckUIState,
    onEvent: (CreateDeckEvent) -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = modifier.padding(paddingValues = paddingValues)
    ) {
        CreateDeckForm(
            title = uiState.title,
            visibility = uiState.visibility,
            maxMembers = uiState.maxMembers,
            isLoading = uiState.isLoading,
            generalErrorMessage = uiState.generalErrorMessage,
            onTitleChange = { onEvent(CreateDeckEvent.OnTitleChange(it)) },
            onVisibilityChange = { onEvent(CreateDeckEvent.OnVisibilityChange(it)) },
            onMaxMembersChange = { onEvent(CreateDeckEvent.OnMaxMembersChange(it)) },
            onSubmit = { onEvent(CreateDeckEvent.OnSubmit) },
        )
    }
}