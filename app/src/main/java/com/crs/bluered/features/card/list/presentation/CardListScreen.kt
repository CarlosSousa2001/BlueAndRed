package com.crs.bluered.features.card.list.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.crs.bluered.R
import com.crs.bluered.shared.domain.entities.CardOptionsResponseEntity
import com.crs.bluered.shared.domain.entities.CardResponseEntity
import com.crs.bluered.ui.components.BRButton
import com.crs.bluered.ui.components.ButtonStyle
import com.crs.bluered.ui.components.states.EmptyState
import com.crs.bluered.ui.components.states.ErrorState
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(
    uiState: CardListUiState,
    onLoadIfNeeded: () -> Unit,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToEditDeckScreen: () -> Unit,
    onStartGameplay: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        onLoadIfNeeded()
    }

    val shouldLoadMore by remember {
        derivedStateOf {
            val total = listState.layoutInfo.totalItemsCount
            if (total == 0) return@derivedStateOf false
            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleIndex >= total - 2
        }
    }

    LaunchedEffect(shouldLoadMore, uiState.canLoadMore) {
        if (shouldLoadMore && uiState.canLoadMore) {
            onLoadMore()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.deckTitle,
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
                },
                actions = {
                    if (uiState.canEdit) {
                        IconButton(onClick = onNavigateToEditDeckScreen) {
                            Icon(
                                painter = painterResource(R.drawable.ic_edit_24),
                                contentDescription = "Editar deck"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        CardListContent(
            uiState = uiState,
            listState = listState,
            paddingValues = paddingValues,
            onRefresh = onRefresh,
            onStartGameplay = onStartGameplay
        )
    }
}

@Composable
private fun CardListContent(
    uiState: CardListUiState,
    listState: androidx.compose.foundation.lazy.LazyListState,
    paddingValues: PaddingValues,
    onRefresh: () -> Unit,
    onStartGameplay: () -> Unit
) {
    when {
        uiState.isLoading && uiState.items.isEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.errorMessage != null && uiState.items.isEmpty() -> {
            ErrorState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                message = uiState.errorMessage,
                onRetry = onRefresh
            )
        }

        uiState.items.isEmpty() -> {

            EmptyState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                image = painterResource(R.drawable.bg_empty_state),
                title = "Nenhum card encontrado",
                children = {
                    BRButton(
                        text = "Criar novo card",
                        onClick = onStartGameplay,
                        style = ButtonStyle.Success,
                    )
                }

            )
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = sizing.md, vertical = sizing.sm),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(sizing.sm)
            ) {
                item(key = "header") {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(sizing.sm)
                    ) {
                        BRButton(
                            text = "Iniciar",
                            onClick = onStartGameplay,
                            style = ButtonStyle.Primary,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "Cards do deck",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                items(
                    items = uiState.items,
                    key = { it.id }
                ) { item ->
                    CardListItem(item = item)
                }

                if (uiState.isLoadingMore) {
                    item(key = "loading_more") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(sizing.md),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                item(key = "bottom_spacing") {
                    Spacer(modifier = Modifier.padding(bottom = sizing.md))
                }
            }
        }
    }
}

@Composable
private fun CardListItem(
    item: CardResponseEntity,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(sizing.sm)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = sizing.x1,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = shape
            )
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = shape
            )
            .padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.xs)
    ) {
        item.options.forEachIndexed { index, option ->
            CardOptionRow(option = option)
            if (index < item.options.lastIndex) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        }
    }
}

@Composable
private fun CardOptionRow(
    option: CardOptionsResponseEntity,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = sizing.xs),
        horizontalArrangement = Arrangement.spacedBy(sizing.sm),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = option.label,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = option.text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
