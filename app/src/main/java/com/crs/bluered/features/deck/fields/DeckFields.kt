package com.crs.bluered.features.deck.fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.core.domain.model.InputFieldState
import com.crs.bluered.ui.components.BRButton
import com.crs.bluered.ui.components.ButtonStyle
import com.crs.bluered.ui.components.RBOutlineTextField
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun DeckFields(
    modifier: Modifier = Modifier,
    title: InputFieldState,
    description: InputFieldState,
    maxMembers: InputFieldState,
    isLoading: Boolean,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onMaxMembersChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.sm)
    ) {
        Text(
            text = "Novo Deck",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
        )

        RBOutlineTextField(
            label = "Título",
            value = title.value,
            valueChange = onTitleChange,
            isError = !title.errorMessage.isNullOrEmpty(),
            errorMessage = title.errorMessage,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        RBOutlineTextField(
            label = "Descrição",
            value = description.value,
            valueChange = onDescriptionChange,
            isError = !title.errorMessage.isNullOrEmpty(),
            errorMessage = description.errorMessage,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        RBOutlineTextField(
            label = "Máximo de membros",
            value = maxMembers.value,
            valueChange = onMaxMembersChange,
            isError = !title.errorMessage.isNullOrEmpty(),
            errorMessage = maxMembers.errorMessage,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
        )

        BRButton(
            text = "Salvar",
            onClick = onSubmit,
            enabled = !isLoading,
            loading = isLoading,
            style = ButtonStyle.Success,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sizing.sm)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun DeckFieldsFields() {
    BlueRedTheme{
        DeckFields(
            title = InputFieldState(
                value = "",
                errorMessage = "Campo obrigatório"
            ),
            description = InputFieldState(
                value = "",
                errorMessage = "Campo obrigatório"
            ),
            maxMembers = InputFieldState(
                value = "",
                errorMessage = "Informe um número"
            ),
            isLoading = false,
            onTitleChange = {},
            onDescriptionChange = {},
            onMaxMembersChange = {},
            onSubmit = {}
        )
    }
}