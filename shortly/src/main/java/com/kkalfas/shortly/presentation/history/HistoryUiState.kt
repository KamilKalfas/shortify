package com.kkalfas.shortly.presentation.history

import com.kkalfas.shortly.data.history.model.LinkEntryModel

data class HistoryUiState(
    val urlInput: String,
    val history: List<LinkEntryModel> = emptyList()
)
