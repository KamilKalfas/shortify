package com.kkalfas.shortly.di

import com.kkalfas.shortly.data.history.api.ShrtcoApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun provideApi(api: ShrtcoApi.Impl): ShrtcoApi
}
