package com.kkalfas.shortly.history.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kkalfas.shortly.data.history.database.model.LinkHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LinkHistoryDao {

    @Query("SELECT * FROM link_history")
    fun observeHistory(): Flow<List<LinkHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLink(link: LinkHistoryEntity)

    @Query("DELETE FROM link_history WHERE uuid = :code")
    suspend fun deleteLinkById(code: String): Int
}
