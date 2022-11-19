package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.history.api.ShrtcoApi
import com.kkalfas.shortly.data.history.model.LinkEntryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class RemoteHistoryDataSource @Inject constructor(
    private val api: ShrtcoApi
) : HistoryDataSource {

    override suspend fun shortenUrl(url: String) {
        val response = api.getShortenUrl(url)
        // TODO save to db
//        return LinkEntity(
//            short = response.result.shortLink,
//            original = response.result.original
//        )
    }

    // noop for Remote
    override fun getLinkHistory(): Flow<List<LinkEntryModel>> = emptyFlow()
}
