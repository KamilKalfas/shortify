package com.kkalfas.shortly.di

import android.content.Context
import androidx.room.Room
import com.kkalfas.shortly.data.history.database.AppDatabase
import com.kkalfas.shortly.data.history.database.HistoryDatabaseAdapter
import com.kkalfas.shortly.data.history.database.HistoryRoomDbAdapter
import com.kkalfas.shortly.data.history.database.LinkHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideHistoryDao(database: AppDatabase): LinkHistoryDao {
        return database.linkHistoryDao()
    }

    @Provides
    @Singleton
    fun provideDatabaseAdapter(dao: LinkHistoryDao): HistoryDatabaseAdapter {
        return HistoryRoomDbAdapter(dao)
    }
}
