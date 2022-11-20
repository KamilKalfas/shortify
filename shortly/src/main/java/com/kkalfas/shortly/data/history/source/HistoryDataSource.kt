package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import com.kkalfas.shortly.data.history.model.LinkEntryModel
import kotlinx.coroutines.flow.Flow

interface HistoryDataSource {

    suspend fun shortenUrl(url: String)

    suspend fun saveLink(link: LinkHistoryEntity)

    suspend fun deleteLink(code: String)

    fun getLinkHistory() : Flow<List<LinkEntryModel>>
}
