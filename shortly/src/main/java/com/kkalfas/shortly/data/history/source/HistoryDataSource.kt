package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.history.model.entities.LinkEntity
import kotlinx.coroutines.flow.Flow

interface HistoryDataSource {
    suspend fun shortenUrl(url: String)
    fun getLinkHistory() : Flow<List<LinkEntity>>
}
