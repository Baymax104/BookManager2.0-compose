package com.baymax104.bookmanager20compose.bean.dto

import android.os.Parcel
import android.os.Parcelable
import com.baymax104.bookmanager20compose.util.PageSerializer
import kotlinx.serialization.Serializable

/**
 * BookDto
 * @author John
 */
@Serializable
data class BookDto(
    var title: String = "",
    @Serializable(PageSerializer::class)
    var page: Int = 0,
    var author: String = "",
    var img: String = "",
    var publisher: String = "",
    var isbn: String = "",
    var summary: String = "",
) : Parcelable {

    constructor(parcel: Parcel) : this() {
        with(parcel) {
            title = readString() ?: ""
            page = readInt()
            author = readString() ?: ""
            img = readString() ?: ""
            publisher = readString() ?: ""
            isbn = readString() ?: ""
            summary = readString() ?: ""
        }
    }

    companion object CREATOR : Parcelable.Creator<BookDto> {
        override fun createFromParcel(parcel: Parcel): BookDto {
            return BookDto(parcel)
        }

        override fun newArray(size: Int): Array<BookDto?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeString(title)
            writeInt(page)
            writeString(author)
            writeString(img)
            writeString(publisher)
            writeString(isbn)
            writeString(summary)
        }
    }
}