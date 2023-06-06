package com.kkalfas.shortly.history.domain

import com.kkalfas.shortly.data.history.model.LinkEntryModel
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    suspend fun shortenUrl(url: String)

    suspend fun deleteLink(code: String)

    fun getLinkHistoryStream(): Flow<List<LinkEntryModel>>
}
