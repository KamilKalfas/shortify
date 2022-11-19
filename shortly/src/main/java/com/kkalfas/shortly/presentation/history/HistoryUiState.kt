package com.kkalfas.shortly.presentation.history

import com.kkalfas.shortly.data.history.model.entities.LinkEntity

data class HistoryUiState(
    val urlInput: String,
    val history: List<LinkEntity> = emptyList()
)
