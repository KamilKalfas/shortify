package com.kkalfas.shortly.history.ui

import com.kkalfas.shortly.data.history.model.LinkEntryModel

data class HistoryUiState(
    val isLoading: Boolean = false,
    val isInputError: Boolean = false,
    val errorMessage: Int = -1,
    val urlInput: String = "",
    val history: List<LinkEntryModel> = emptyList()
)
