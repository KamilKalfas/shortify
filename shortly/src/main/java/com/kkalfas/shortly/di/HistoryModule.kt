package com.kkalfas.shortly.di

import com.kkalfas.shortly.history.data.source.HistoryDataSource
import com.kkalfas.shortly.history.data.source.LocalHistoryDataSource
import com.kkalfas.shortly.history.data.source.RemoteHistoryDataSource
import com.kkalfas.shortly.history.data.repository.HistoryRepositoryImpl
import com.kkalfas.shortly.history.domain.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val REMOTE_SOURCE = "remoteDataSource"
const val LOCAL_SOURCE = "localDataSource"

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoryModule {

    @Singleton
    @Binds
    @Named(REMOTE_SOURCE)
    abstract fun provideRemoteHistoryDataSource(dataSource: RemoteHistoryDataSource): HistoryDataSource

    @Singleton
    @Binds
    @Named(LOCAL_SOURCE)
    abstract fun provideLocalHistoryDataSource(dataSource: LocalHistoryDataSource): HistoryDataSource

    @Singleton
    @Binds
    abstract fun provideHistoryRepository(repository: HistoryRepositoryImpl): HistoryRepository
}
