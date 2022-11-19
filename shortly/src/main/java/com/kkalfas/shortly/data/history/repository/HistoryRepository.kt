package com.kkalfas.shortly.data.history.repository

import com.kkalfas.shortly.data.history.model.entities.LinkEntity
import com.kkalfas.shortly.data.history.source.HistoryDataSource
import com.kkalfas.shortly.di.REMOTE_SOURCE
import javax.inject.Inject
import javax.inject.Named

interface HistoryRepository {
    suspend fun shortenUrl(url: String) : LinkEntity

    class Impl @Inject constructor(
        @Named(REMOTE_SOURCE) private val remoteDataSource: HistoryDataSource
    ) : HistoryRepository {

        override suspend fun shortenUrl(url: String) = remoteDataSource.getShortUrl(url)
    }
}
