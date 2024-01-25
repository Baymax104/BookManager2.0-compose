package com.baymax104.bookmanager20compose.bean.vo

import java.util.Date

/**
 * 读过页BookView
 * @author John
 * @since 2024/1/25
 */
data class FinishBookView(
    val id: Int = 0,
    var title: String? = null,
    var page: Int = 0,
    var author: String? = null,
    var progress: Int = 0,
    var endTime: Date? = null,
    var image: String? = null,
    var publisher: String? = null,
    var isbn: String? = null,
    var description: String? = null,
    var rank: Int = -1,
)
