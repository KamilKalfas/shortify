package com.kkalfas.shortly.presentation.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkalfas.shortly.AppCoroutineDispatchers
import com.kkalfas.shortly.domain.history.usecase.GetLinkHistoryUseCase
import com.kkalfas.shortly.domain.history.usecase.ShortenUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val STATE_KEY_INPUT = "input"

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val savedStateHandle: SavedStateHandle,
    private val shortenUrlUseCase: ShortenUrlUseCase,
    private val linkHistoryUseCase: GetLinkHistoryUseCase
) : ViewModel() {

    private val _inputValue = savedStateHandle.getStateFlow(STATE_KEY_INPUT, "")
    private val _isLoading = MutableStateFlow(false)
    private val _history = linkHistoryUseCase.invoke()

    val state: StateFlow<HistoryUiState> = combine(
        _inputValue, _isLoading, _history
    ) { input, isLoading, history ->
        HistoryUiState(
            isLoading = isLoading,
            urlInput = input,
            history = history
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HistoryUiState()
    )

    fun onUrlChanged(text: String) {
        savedStateHandle[STATE_KEY_INPUT] = text
    }

    fun onShortenUrl() {
        viewModelScope.launch(appCoroutineDispatchers.io) {
            shortenUrlUseCase.invoke(state.value.urlInput)
            linkHistoryUseCase.invoke()
        }
    }
}
