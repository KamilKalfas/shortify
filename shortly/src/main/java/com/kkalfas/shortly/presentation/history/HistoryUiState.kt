package com.kkalfas.shortly.presentation.history

import com.kkalfas.shortly.data.history.model.LinkEntryModel

data class HistoryUiState(
    val isLoading: Boolean = false,
    val isInputError: Boolean = false,
    val errorMessage: Int = -1,
    val urlInput: String = "",
    val history: List<LinkEntryModel> = emptyList()
)
