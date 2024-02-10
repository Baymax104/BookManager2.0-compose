package com.baymax104.bookmanager20compose.bean.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

/**
 * 读过页BookView
 * @author John
 * @since 2024/1/25
 */
@Parcelize
data class FinishBookView(
    var id: Int = 0,
    var title: String = "未填写",
    var page: Int = 0,
    var author: String = "佚名",
    var progress: Int = 0,
    var endTime: Date = Date(),
    var image: String = "",
    var publisher: String = "",
    var isbn: String = "",
    var description: String = "",
    var rank: Int = -1,
) : Parcelable

