package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.FunctionalityNotAvailable
import com.kkalfas.shortly.data.ShrtcoApiError
import com.kkalfas.shortly.data.history.api.ShrtcoApi
import com.kkalfas.shortly.data.history.database.HistoryDatabaseAdapter
import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import com.kkalfas.shortly.data.history.model.LinkEntryModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteHistoryDataSource @Inject constructor(
    private val api: ShrtcoApi,
    private val databaseAdapter: HistoryDatabaseAdapter,
) : HistoryDataSource {

    override suspend fun shortenUrl(url: String) {
        val response = api.getShortenUrl(url)
        if (response.ok) {
            val result = requireNotNull(response.result)
            databaseAdapter.saveLink(
                LinkHistoryEntity(
                    code = result.code,
                    shorted = result.shortLink,
                    original = result.original
                )
            )
        } else throw ShrtcoApiError(requireNotNull(response.error), requireNotNull(response.errorMessage))
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
