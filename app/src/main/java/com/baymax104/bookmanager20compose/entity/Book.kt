package com.baymax104.bookmanager20compose.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baymax104.bookmanager20compose.util.PageSerializer
import com.baymax104.bookmanager20compose.util.toDate
import com.baymax104.bookmanager20compose.util.toDateString
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
data class Book(
    @Transient
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String? = null,
    @Serializable(PageSerializer::class)
    var page: Int = 0,
    var author: String? = null,
    var progress: Int = 0,
    @Transient
    var startTime: Date? = null,
    @Transient
    var endTime: Date? = null,
    @SerialName("img")
    var photo: String? = null,
    var publisher: String? = null,
    var isbn: String? = null,
    @SerialName("summary")
    var description: String? = null,
    var tableRank: Int = -1,
) : Parcelable {

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()
        page = parcel.readInt()
        author = parcel.readString()
        progress = parcel.readInt()
        photo = parcel.readString()
        publisher = parcel.readString()
        isbn = parcel.readString()
        description = parcel.readString()
        tableRank = parcel.readInt()
        startTime = parcel.readString().toDate()
        endTime = parcel.readString().toDate()
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeInt(id)
            writeString(title)
            writeInt(page)
            writeString(author)
            writeInt(progress)
            writeString(photo)
            writeString(publisher)
            writeString(isbn)
            writeString(description)
            writeInt(tableRank)
            writeString(startTime.toDateString())
            writeString(endTime.toDateString())
        }
    }
}