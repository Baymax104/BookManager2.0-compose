package com.baymax104.bookmanager20compose.bean.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * BookPo
 * @author John
 * @since 2024/1/25
 */
@Entity(tableName = "Book")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var page: Int = 0,
    var author: String = "",
    var progress: Int = 0,
    var startTime: Date? = null,
    var endTime: Date? = null,
    var image: String = "",
    var publisher: String = "",
    var isbn: String = "",
    var description: String = "",
    var rank: Int = -1,
)
