package com.crs.bluered.features.deck.list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crs.bluered.R
import com.crs.bluered.core.domain.model.PaginationMeta
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.features.deck.list.presentation.DeckListUiState
import com.crs.bluered.shared.domain.enums.DeckListScope
import com.crs.bluered.shared.domain.enums.DeckVisibility
import com.crs.bluered.ui.components.states.EmptyState
import com.crs.bluered.ui.components.states.ErrorState
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun DeckListContainer(
    state: DeckListUiState,
    onLoadIfNeeded: () -> Unit,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    onChangeScope: (DeckListScope) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    val gridState = rememberLazyGridState()

    LaunchedEffect(Unit) { onLoadIfNeeded() }

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
        if (shouldLoadMore && state.canLoadMore) onLoadMore()
    }

    val tabs = remember { listOf("Global", "Privados", "Meus Decks") }

    val selectedTabIndex = remember(state.scope) {
        when (state.scope) {
            DeckListScope.GLOBAL -> 0
            DeckListScope.PRIVATE -> 1
            DeckListScope.ME -> 2
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(sizing.sm),
    ) {

        PrimaryTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            divider = {}
        ) {
            tabs.forEachIndexed { index, label ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        when (index) {
                            0 -> onChangeScope(DeckListScope.GLOBAL)
                            1 -> onChangeScope(DeckListScope.PRIVATE)
                            2 -> onChangeScope(DeckListScope.ME)
                        }
                    },
                    text = {
                        Text(
                            text = label.uppercase(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(sizing.lg))

        when {
            state.isLoading && state.items.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(sizing.sm))
                    Text("Carregando...")
                }
            }

            state.errorMessage != null && state.items.isEmpty() -> {
                ErrorState(
                    message = state.errorMessage,
                    onRetry = onRefresh,
                    modifier = Modifier.fillMaxSize()
                )
            }

            state.items.isEmpty() -> {
                EmptyState(
                    modifier = Modifier.fillMaxSize(),
                    image = painterResource(R.drawable.bg_empty_state),
                    title = "Nenhum deck encontrado",
                )
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

@Preview(showBackground = true)
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
                scope = DeckListScope.GLOBAL,
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
            onChangeScope = {},
            paddingValues = PaddingValues(0.dp)
        )
    }
}
