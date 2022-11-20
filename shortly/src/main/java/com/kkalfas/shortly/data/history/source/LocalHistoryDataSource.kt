package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.FunctionalityNotAvailable
import com.kkalfas.shortly.data.history.database.HistoryDatabaseAdapter
import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import com.kkalfas.shortly.data.history.model.LinkEntryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalHistoryDataSource @Inject constructor(
    private val databaseAdapter: HistoryDatabaseAdapter
) : HistoryDataSource {

    override suspend fun shortenUrl(url: String) {
        // noop for Local
        throw FunctionalityNotAvailable()
    }

    override suspend fun saveLink(link: LinkHistoryEntity) {
        databaseAdapter.saveLink(link)
    }

    override suspend fun deleteLink(code: String) {
        databaseAdapter.deleteLink(code)
    }

    override fun getLinkHistory(): Flow<List<LinkEntryModel>> {
        return databaseAdapter.getLinkHistoryStream().map { all ->
            all.map {
                LinkEntryModel(
                    code = it.code,
                    short = it.shorted,
                    original = it.original
                )
            }
        }
    }
}
