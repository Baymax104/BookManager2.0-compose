package com.baymax104.bookmanager20compose.entity

import java.util.Date

/**
 * Book实体类
 * @author John
 */
class Book {
    var id = 0
    var name: String? = null
    var page = 0
    var author: String? = null
    var progress = 0
    var startTime: Date? = null
    var endTime: Date? = null
    var photo: String? = null
    var publisher: String? = null
    var isbn: String? = null
    var description: String? = null
    var tableRank = -1
}