package com.crs.bluered.features.card.create.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.crs.bluered.features.card.create.presentation.CreateCardEvent
import com.crs.bluered.features.card.create.presentation.CreateCardUIState

@Composable
fun CreateCardContainer(
    modifier: Modifier = Modifier,
    uiState: CreateCardUIState,
    onEvent: (CreateCardEvent) -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = modifier.padding(paddingValues = paddingValues)
    ) {
        CreateCardForm(
            title = uiState.text,
            label = uiState.label,
            isLoading = uiState.isLoading,
            generalErrorMessage = uiState.generalErrorMessage,
            onTitleChange = {onEvent(CreateCardEvent.OnTextChange(it))},
            onLabelChange = {onEvent(CreateCardEvent.OnLabelChange(it))},
            onSubmit = {onEvent(CreateCardEvent.OnSubmit)}
        )
    }
}