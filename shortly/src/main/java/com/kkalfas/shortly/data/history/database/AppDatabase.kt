package com.kkalfas.shortly.data.history.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity

@Database(entities = [LinkHistoryEntity::class], version = AppDatabase.VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
        const val NAME = "shortly_database"
    }

    abstract fun linkHistoryDao(): LinkHistoryDao
}
