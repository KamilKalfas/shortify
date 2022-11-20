package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.FunctionalityNotAvailable
import com.kkalfas.shortly.data.history.api.ShrtcoApi
import com.kkalfas.shortly.data.history.database.HistoryDatabaseAdapter
import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import com.kkalfas.shortly.data.history.model.LinkEntryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class RemoteHistoryDataSource @Inject constructor(
    private val api: ShrtcoApi,
    private val databaseAdapter: HistoryDatabaseAdapter,
) : HistoryDataSource {

    override suspend fun shortenUrl(url: String) {
        val response = api.getShortenUrl(url).result
        databaseAdapter.saveLink(
            LinkHistoryEntity(
                code = response.code,
                shorted = response.shortLink,
                original = response.original
            )
        )
    }

    override suspend fun saveLink(link: LinkHistoryEntity) {
        // noop for Remote
        throw FunctionalityNotAvailable()
    }

    override fun getLinkHistory(): Flow<List<LinkEntryModel>> {
        // noop for Remote
        throw FunctionalityNotAvailable()
    }
}
