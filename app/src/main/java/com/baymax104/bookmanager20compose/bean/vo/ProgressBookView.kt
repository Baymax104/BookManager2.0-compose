package com.baymax104.bookmanager20compose.bean.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date


/**
 * 进度页BookView
 * @author John
 * @since 2024/1/25
 */
@Parcelize
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
    var isbn: String = ""
) : Parcelable
