package com.baymax104.bookmanager20compose.bean.dto

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
)