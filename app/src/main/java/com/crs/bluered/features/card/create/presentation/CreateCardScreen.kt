package com.crs.bluered.features.card.create.presentation

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
import androidx.compose.ui.unit.sp
import com.crs.bluered.R
import com.crs.bluered.features.card.create.presentation.components.CreateCardContainer
import com.crs.bluered.features.deck.create.presentation.components.CreateDeckContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCardScreen(
    modifier: Modifier = Modifier,
    uiState: CreateCardUIState,
    onEvent: (CreateCardEvent) -> Unit,
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
            CreateCardContainer(
                modifier = modifier,
                uiState = uiState,
                onEvent = onEvent,
                paddingValues = paddingValues
            )
        }
    )
}