package com.kkalfas.shortly.history.domain

import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryDatabaseAdapter {

    fun getLinkHistoryStream() : Flow<List<LinkHistoryEntity>>

    suspend fun saveLink(link: LinkHistoryEntity)

    suspend fun deleteLink(code: String) : Int
}
