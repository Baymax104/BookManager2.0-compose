package com.baymax104.bookmanager20compose.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baymax104.bookmanager20compose.util.PageSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.Date

/**
 * Book实体类
 * @author John
 */
@Serializable
@Entity
class Book {
    @Transient
    @PrimaryKey
    var id = 0
    var title: String? = null
    @Serializable(PageSerializer::class)
    var page = 0
    var author: String? = null
    var progress = 0
    @Transient
    var startTime: Date? = null
    @Transient
    var endTime: Date? = null
    @SerialName("img")
    var photo: String? = null
    var publisher: String? = null
    var isbn: String? = null
    @SerialName("summary")
    var description: String? = null
    var tableRank = -1
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (page != other.page) return false
        if (author != other.author) return false
        if (progress != other.progress) return false
        if (startTime != other.startTime) return false
        if (endTime != other.endTime) return false
        if (photo != other.photo) return false
        if (publisher != other.publisher) return false
        if (isbn != other.isbn) return false
        if (description != other.description) return false
        if (tableRank != other.tableRank) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + page
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + progress
        result = 31 * result + (startTime?.hashCode() ?: 0)
        result = 31 * result + (endTime?.hashCode() ?: 0)
        result = 31 * result + (photo?.hashCode() ?: 0)
        result = 31 * result + (publisher?.hashCode() ?: 0)
        result = 31 * result + (isbn?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + tableRank
        return result
    }

    override fun toString(): String {
        return "Book(id=$id, title=$title, page=$page, author=$author, progress=$progress, startTime=$startTime, endTime=$endTime, photo=$photo, publisher=$publisher, isbn=$isbn, description=$description, tableRank=$tableRank)"
    }
}