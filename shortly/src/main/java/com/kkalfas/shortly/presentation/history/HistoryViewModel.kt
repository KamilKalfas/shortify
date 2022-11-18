package com.kkalfas.shortly.presentation.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val STATE_KEY_INPUT = "input"

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = MutableStateFlow(HistoryUiState(
        urlInput = savedStateHandle.get<String>(STATE_KEY_INPUT) ?: ""
    ))

    fun onUrlChanged(text: String) {
        state.update { prevState ->
            prevState.copy(
                urlInput = text
            )
        }
        savedStateHandle[STATE_KEY_INPUT] = text
    }
}
