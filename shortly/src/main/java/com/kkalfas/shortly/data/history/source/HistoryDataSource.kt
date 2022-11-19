package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.history.model.LinkEntryModel
import kotlinx.coroutines.flow.Flow

interface HistoryDataSource {
    suspend fun shortenUrl(url: String)
    fun getLinkHistory() : Flow<List<LinkEntryModel>>
}
