package com.kkalfas.shortly.data.history.source

import com.kkalfas.shortly.data.history.model.entities.LinkEntity

interface HistoryDataSource {
    suspend fun getShortUrl(url: String): LinkEntity
}
