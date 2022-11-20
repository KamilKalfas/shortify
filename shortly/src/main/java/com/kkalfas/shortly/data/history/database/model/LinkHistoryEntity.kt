package com.kkalfas.shortly.data.history.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "link_history")
data class LinkHistoryEntity(
    @PrimaryKey @ColumnInfo(name = "uuid") val code: String,
    @ColumnInfo(name = "original_link") val original: String,
    /***
     * Cool story... "short" can't be used as a name because kapt
     * changes the name to pX probably because it's same as type name
     */
    @ColumnInfo(name = "short_link") val shorted: String
)
