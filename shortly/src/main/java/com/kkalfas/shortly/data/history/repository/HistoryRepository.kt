package com.kkalfas.shortly.data.history.repository

import com.kkalfas.shortly.data.history.model.LinkEntryModel
import com.kkalfas.shortly.data.history.source.HistoryDataSource
import com.kkalfas.shortly.di.LOCAL_SOURCE
import com.kkalfas.shortly.di.REMOTE_SOURCE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

interface HistoryRepository {

    suspend fun shortenUrl(url: String)

    suspend fun deleteLink(code: String)

    fun getLinkHistoryStream() : Flow<List<LinkEntryModel>>

    class Impl @Inject constructor(
        @Named(REMOTE_SOURCE) private val remoteDataSource: HistoryDataSource,
        @Named(LOCAL_SOURCE) private val localDataSource: HistoryDataSource
    ) : HistoryRepository {

        override suspend fun shortenUrl(url: String) = remoteDataSource.shortenUrl(url)

        override suspend fun deleteLink(code: String) = localDataSource.deleteLink(code)

        override fun getLinkHistoryStream(): Flow<List<LinkEntryModel>> = localDataSource.getLinkHistory()
    }
}
