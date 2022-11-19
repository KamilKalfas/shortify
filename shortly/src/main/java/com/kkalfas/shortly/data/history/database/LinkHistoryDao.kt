package com.kkalfas.shortly.data.history.database

import androidx.room.Dao
import androidx.room.Query
import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LinkHistoryDao {

    @Query("SELECT * FROM link_history")
    fun observeHistory(): Flow<List<LinkHistoryEntity>>
}

