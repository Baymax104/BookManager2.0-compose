package com.baymax104.bookmanager20compose.repo

import com.baymax104.bookmanager20compose.repo.database.BookMapper
import com.baymax104.bookmanager20compose.repo.database.createMapper
import com.baymax104.bookmanager20compose.repo.web.BookService
import com.baymax104.bookmanager20compose.repo.web.createService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository
 * @author John
 */
object Repository {

    val bookService: BookService by createService()
    val bookMapper: BookMapper by createMapper()

    suspend fun requestBook(isbn: String) = withContext(Dispatchers.IO) {
        bookService.requestBook(isbn)
    }

    suspend fun queryAllProgressBook() = bookMapper.queryAllProcess()

}