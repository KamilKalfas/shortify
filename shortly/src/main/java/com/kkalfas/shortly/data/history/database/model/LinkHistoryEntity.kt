package com.kkalfas.shortly.data.history.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "link_history")
data class LinkHistoryEntity(
    @PrimaryKey @ColumnInfo(name = "uuid") val code: String,
    @ColumnInfo(name = "short_link") val original: String,
    @ColumnInfo(name = "original_link") val short: String,
)
