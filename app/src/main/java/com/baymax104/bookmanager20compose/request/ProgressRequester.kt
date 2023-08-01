package com.baymax104.bookmanager20compose.request

import com.baymax104.bookmanager20compose.base.Requester
import com.baymax104.bookmanager20compose.entity.Book
import com.baymax104.bookmanager20compose.repo.Repository

/**
 * Progress Requester
 * @author John
 */
class ProgressRequester : Requester() {

    val repo = Repository

    suspend fun requestBook(isbn: String): Book? {
        val response = repo.requestBook(isbn)
        return when (response.errcode) {
            0 -> response.data
            else -> null
        }
    }

    suspend fun queryProgressBook(): MutableList<Book> = repo.queryAllProgressBook()


}