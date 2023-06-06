package com.kkalfas.shortly.history.data.repository

import com.kkalfas.shortly.data.history.model.LinkEntryModel
import com.kkalfas.shortly.history.data.source.HistoryDataSource
import com.kkalfas.shortly.di.LOCAL_SOURCE
import com.kkalfas.shortly.di.REMOTE_SOURCE
import com.kkalfas.shortly.history.domain.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class HistoryRepositoryImpl @Inject constructor(
    @Named(REMOTE_SOURCE) private val remoteDataSource: HistoryDataSource,
    @Named(LOCAL_SOURCE) private val localDataSource: HistoryDataSource
) : HistoryRepository {

    override suspend fun shortenUrl(url: String) = remoteDataSource.shortenUrl(url)

    override suspend fun deleteLink(code: String) = localDataSource.deleteLink(code)

    override fun getLinkHistoryStream(): Flow<List<LinkEntryModel>> = localDataSource.getLinkHistory()
}
