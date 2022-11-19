package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.history.database.HistoryDatabaseAdapter
import com.kkalfas.shortly.data.history.model.LinkEntryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalHistoryDataSource @Inject constructor(
    private val databaseAdapter: HistoryDatabaseAdapter
) : HistoryDataSource {

    override suspend fun shortenUrl(url: String) {
        // noop for Local
    }

    override fun getLinkHistory(): Flow<List<LinkEntryModel>> {
        return databaseAdapter.getLinkHistoryStream().map { all ->
            all.map {
                LinkEntryModel(it.short, it.original)
            }
        }
    }
}
