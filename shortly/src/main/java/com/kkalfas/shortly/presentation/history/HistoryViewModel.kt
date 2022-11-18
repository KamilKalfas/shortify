package com.kkalfas.shortly.presentation.history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel() {

    val uiState = MutableStateFlow(HistoryUiState())

    fun onUrlChanged(text: String) {
        uiState.update { prevState ->
            prevState.copy(
                urlInput = text
            )
        }
    }
}
