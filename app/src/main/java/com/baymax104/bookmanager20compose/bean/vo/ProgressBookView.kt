package com.baymax104.bookmanager20compose.bean.vo


/**
 * 进度页BookView
 * @author John
 * @since 2024/1/25
 */
data class ProgressBookView(
    val id: Int = 0,
    var title: String = "未填写",
    var author: String = "佚名",
    var page: Int = 0,
    val startTime: String = "",
    val image: String = "",
    var progress: Int = 0,
    var rank: Int = -1,
    val description: String = "",
    val publisher: String = "",
)
