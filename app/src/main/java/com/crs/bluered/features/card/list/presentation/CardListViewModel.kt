package com.crs.bluered.features.card.list.presentation

import androidx.lifecycle.ViewModel
import com.crs.bluered.features.card.list.domain.usecase.CardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val cardListUseCase: CardListUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(CardListUiState())
    val uiState: StateFlow<CardListUiState> = _uiState.asStateFlow()


}