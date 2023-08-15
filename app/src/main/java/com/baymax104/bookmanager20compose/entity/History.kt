package com.baymax104.bookmanager20compose.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.baymax104.bookmanager20compose.util.toDateString
import java.util.Date

/**
 * 历史记录实体类
 * @author John
 */
@Entity
class History(
    book: Book
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    var bookId = book.id

    var updateTime = Date()

    @Ignore
    var start = 0

    @Ignore
    var end = 0

    var total = book.page

    var duplicate = false

    @get:Ignore
    val type: Type
        get() = when {
            start == 0 && end == 0 -> Start
            duplicate -> Duplicate(start, end, total)
            else -> Process(start, end, total)
        }

    @ColumnInfo(name = "start")
    var startDB = 0
        get() = start
        set(value) {
            field = value
            start = value
        }

    @ColumnInfo(name = "end")
    var endDB = 0
        get() = end
        set(value) {
            field = value
            end = value
        }

    constructor() : this(Book())

    sealed class Type
    object Start : Type()
    data class Process(var start: Int, val end: Int, val total: Int) : Type()
    data class Duplicate(var start: Int, val end: Int, val total: Int) : Type()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as History

        if (id != other.id) return false
        if (bookId != other.bookId) return false
        if (start != other.start) return false
        if (end != other.end) return false
        if (updateTime != other.updateTime) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + bookId
        result = 31 * result + start
        result = 31 * result + end
        result = 31 * result + updateTime.hashCode()
        result = 31 * result + total
        return result
    }

    override fun toString(): String {
        return "History(id=$id, bookId=$bookId, start=$start, end=$end, updateTime=${updateTime.toDateString()}, total=$total, duplicate=$duplicate)"
    }
}