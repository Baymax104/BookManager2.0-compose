package com.baymax104.bookmanager20compose.bean.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * HistoryPo
 * @author John
 * @since 2024/1/25
 */
@Entity(tableName = "History")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var bookId: Int = 0,
    var updateTime: Date = Date(),
    var page: Int = 0,
    var start: Int = 0,
    var end: Int = 0
)
