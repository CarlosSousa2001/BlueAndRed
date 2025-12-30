package com.crs.bluered.features.deck.list.presentation
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.R
import com.crs.bluered.core.domain.model.PaginationMeta
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.features.deck.list.presentation.components.DeckListContainer
import com.crs.bluered.shared.domain.enums.DeckListScope
import com.crs.bluered.shared.domain.enums.DeckVisibility
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun DeckListScreen(
    modifier: Modifier = Modifier,
    uiState: DeckListUiState,
    onLoadIfNeeded: () -> Unit,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    onChangeScope: (DeckListScope) -> Unit,
    onNavigateToCreateDeckScreen: () -> Unit,
    onOpenJoinDeckModal: () -> Unit = {}
) {

    var fabMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            Box {
                FloatingActionButton(
                    onClick = { fabMenuExpanded = true },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    // Use um ícone seu (recomendado, já que você não tem Icons)
                    Icon(
                        painter = painterResource(R.drawable.ic_add_24),
                        contentDescription = "Ações"
                    )
                }

                DropdownMenu(
                    expanded = fabMenuExpanded,
                    onDismissRequest = { fabMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Novo deck") },
                        onClick = {
                            fabMenuExpanded = false
                            onNavigateToCreateDeckScreen()
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_add_24),
                                contentDescription = null
                            )
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Entrar em um deck") },
                        onClick = {
                            fabMenuExpanded = false
                            onOpenJoinDeckModal() // ✅ depois você troca pra abrir modal
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_key_24),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        },
        content = { paddingValues ->
            DeckListContainer(
                modifier = modifier,
                state = uiState,
                onLoadIfNeeded = onLoadIfNeeded,
                onRefresh = onRefresh,
                onLoadMore = onLoadMore,
                onChangeScope = onChangeScope,
                paddingValues = paddingValues
            )
        }
    )
}

@Preview(
    name = "Deck List – Default",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DeckListScreenPreview() {
    BlueRedTheme {
        DeckListScreen(
            uiState = DeckListUiState(
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
                    ),
                    DeckListItem(
                        id = "3",
                        title = "Choices Game",
                        maxMembers = null,
                        ownerId = "user-2",
                        visibility = DeckVisibility.PRIVATE,
                        isOfficial = false,
                        isActive = true,
                        createdAt = "2025-01-02",
                        cardsCount = 5,
                        optionsCount = 10
                    ),
                    DeckListItem(
                        id = "4",
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
            onNavigateToCreateDeckScreen = {}
        )
    }
}