package com.baymax104.bookmanager20compose.repo

import com.baymax104.bookmanager20compose.entity.Book
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

    private val bookService: BookService by createService()
    private val bookMapper: BookMapper by createMapper()

    suspend fun requestBook(isbn: String) = withContext(Dispatchers.IO) {
        bookService.requestBook(isbn)
    }

    suspend fun queryAllProgressBook() = withContext(Dispatchers.IO) {
        bookMapper.queryAllProcess()
    }

    suspend fun insertProgressBook(book: Book) = withContext(Dispatchers.IO) {

    }

}