package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.history.api.ShrtcoApi
import com.kkalfas.shortly.data.history.model.entities.LinkEntity
import javax.inject.Inject

class RemoteHistoryDataSource @Inject constructor(
    private val api: ShrtcoApi
) : HistoryDataSource {

    override suspend fun getShortUrl(url: String): LinkEntity {
        val response = api.getShortenUrl(url)
        return LinkEntity(
            short = response.result.shortLink,
            original = response.result.original
        )
    }
}
