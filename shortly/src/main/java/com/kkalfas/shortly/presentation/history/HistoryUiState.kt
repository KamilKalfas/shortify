package com.kkalfas.shortly.presentation.history

data class HistoryUiState(
    val urlInput: String = "",
    val history: List<String> = emptyList()
)
