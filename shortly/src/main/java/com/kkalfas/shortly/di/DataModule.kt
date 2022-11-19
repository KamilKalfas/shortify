package com.kkalfas.shortly.di

import com.kkalfas.shortly.data.history.repository.HistoryRepository
import com.kkalfas.shortly.data.history.source.HistoryDataSource
import com.kkalfas.shortly.data.history.source.RemoteHistoryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val REMOTE_SOURCE = "remoteDataSource"

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    @Named(REMOTE_SOURCE)
    abstract fun provideRemoteHistoryDataSource(dataSource: RemoteHistoryDataSource): HistoryDataSource

    @Singleton
    @Binds
    abstract fun provideHistoryRepository(repository: HistoryRepository.Impl): HistoryRepository
}
