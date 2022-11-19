package com.kkalfas.shortly.presentation.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkalfas.shortly.AppCoroutineDispatchers
import com.kkalfas.shortly.domain.history.usecase.ShortenUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val STATE_KEY_INPUT = "input"

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val savedStateHandle: SavedStateHandle,
    private val shortenUrlUseCase: ShortenUrlUseCase
) : ViewModel() {

    val state = MutableStateFlow(
        HistoryUiState(
            urlInput = savedStateHandle.get<String>(STATE_KEY_INPUT) ?: ""
        )
    )

    fun onUrlChanged(text: String) {
        state.update { prevState ->
            prevState.copy(
                urlInput = text
            )
        }
        savedStateHandle[STATE_KEY_INPUT] = text
    }

    fun onShortenUrl() {
        viewModelScope.launch(appCoroutineDispatchers.io) {
            val link = shortenUrlUseCase.invoke(state.value.urlInput)
            state.update { prevState ->
                prevState.copy(history = listOf(link))
            }
        }
    }
}
