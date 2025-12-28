package com.crs.bluered.features.deck.list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.bluered.core.domain.model.PaginationMeta
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.features.deck.list.presentation.components.DeckItemsList
import com.crs.bluered.shared.domain.enums.DeckVisibility
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun DeckListContainer(
    state: DeckListUiState,
    onLoadIfNeeded: () -> Unit,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    onChangeVisibility: (String?) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    val gridState = rememberLazyGridState()

    // ✅ primeira carga (recomendado no Compose)
    LaunchedEffect(Unit) {
        onLoadIfNeeded()
    }

    // ✅ gatilho de paginação (chegou perto do fim)
    val shouldLoadMore by remember {
        derivedStateOf {
            val total = gridState.layoutInfo.totalItemsCount
            if (total == 0) return@derivedStateOf false

            val lastVisibleIndex = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val threshold = 3
            lastVisibleIndex >= (total - threshold)
        }
    }

    LaunchedEffect(shouldLoadMore, state.canLoadMore) {
        if (shouldLoadMore && state.canLoadMore) {
            onLoadMore()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val current = state.visibility

            Button(
                onClick = { onChangeVisibility(null) },
                enabled = current != null
            ) { Text("All") }

            Button(
                onClick = { onChangeVisibility("PUBLIC") },
                enabled = current != "PUBLIC"
            ) { Text("Public") }

            Button(
                onClick = { onChangeVisibility("PRIVATE") },
                enabled = current != "PRIVATE"
            ) { Text("Private") }

            OutlinedButton(
                onClick = onRefresh
            ) { Text("Refresh") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ✅ estados da tela
        when {
            state.isLoading && state.items.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Carregando...")
                }
            }

            state.errorMessage != null && state.items.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.errorMessage,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = onRefresh) { Text("Tentar novamente") }
                }
            }

            state.items.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Nenhum deck encontrado.")
                }
            }

            else -> {
                DeckItemsList(
                    items = state.items,
                    gridState = gridState,
                    isLoadingMore = state.isLoadingMore,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DeckListContainerPreview() {
    BlueRedTheme {
        DeckListContainer(
            state = DeckListUiState(
                items = listOf(
                    DeckListItem(
                        id = "1",
                        title = "Blue or Red?",
                        maxMembers = 10,
                        ownerId = "user-1",
                        visibility = DeckVisibility.PUBLIC,
                        isOfficial = true,
                        isActive = true,
                        createdAt = "2025-01-01",
                        cardsCount = 12,
                        optionsCount = 24
                    ),
                    DeckListItem(
                        id = "2",
                        title = "Choices Game",
                        maxMembers = null,
                        ownerId = "user-2",
                        visibility = DeckVisibility.PRIVATE,
                        isOfficial = false,
                        isActive = true,
                        createdAt = "2025-01-02",
                        cardsCount = 5,
                        optionsCount = 10
                    )
                ),
                page = 1,
                perPage = 10,
                visibility = null,
                isOfficial = null,
                isLoading = false,
                isLoadingMore = false,
                meta = PaginationMeta(
                    currentPage = 1,
                    perPage = 10,
                    nextPage = 2,
                    previousPage = null,
                    totalItems = 100,
                    totalPages = 10
                ),
                errorMessage = null
            ),
            onLoadIfNeeded = {},
            onRefresh = {},
            onLoadMore = {},
            onChangeVisibility = {},
            paddingValues = PaddingValues(0.dp)
        )
    }
}
