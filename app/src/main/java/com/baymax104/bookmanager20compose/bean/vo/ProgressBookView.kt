package com.baymax104.bookmanager20compose.bean.vo

import android.os.Parcel
import android.os.Parcelable
import com.baymax104.bookmanager20compose.util.toDate
import com.baymax104.bookmanager20compose.util.toDateString
import java.util.Date


/**
 * 进度页BookView
 * @author John
 * @since 2024/1/25
 */
data class ProgressBookView(
    var id: Int = 0,
    var title: String = "未填写",
    var author: String = "佚名",
    var page: Int = 0,
    var startTime: Date = Date(),
    var image: String = "",
    var progress: Int = 0,
    var rank: Int = -1,
    var description: String = "",
    var publisher: String = "",
): Parcelable {

    constructor(parcel: Parcel) : this() {
        with(parcel) {
            id = readInt()
            title = readString() ?: ""
            author = readString() ?: ""
            page = readInt()
            startTime = readString().toDate() ?: Date()
            image = readString() ?: ""
            progress = readInt()
            rank = readInt()
            publisher = readString() ?: ""
            description = readString() ?: ""
        }
    }

    companion object CREATOR : Parcelable.Creator<ProgressBookView> {
        override fun createFromParcel(parcel: Parcel): ProgressBookView {
            return ProgressBookView(parcel)
        }

        override fun newArray(size: Int): Array<ProgressBookView?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeInt(id)
            writeString(title)
            writeString(author)
            writeInt(page)
            writeString(startTime.toDateString())
            writeString(image)
            writeInt(progress)
            writeInt(rank)
            writeString(publisher)
            writeString(description)
        }
    }
}
