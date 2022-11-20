package com.kkalfas.shortly.data.history.database

import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryRoomDbAdapter @Inject constructor(
    private val historyDao: LinkHistoryDao
) : HistoryDatabaseAdapter {

    override fun getLinkHistoryStream(): Flow<List<LinkHistoryEntity>> = historyDao.observeHistory()

    override suspend fun saveLink(link: LinkHistoryEntity): Unit = historyDao.insertLink(link)

    override suspend fun deleteLink(code: String) = historyDao.deleteLinkById(code)
}
