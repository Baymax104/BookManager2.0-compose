package com.baymax104.bookmanager20compose.repo

import androidx.room.withTransaction
import com.baymax104.bookmanager20compose.bean.dto.BookDto
import com.baymax104.bookmanager20compose.repo.database.BookDao
import com.baymax104.bookmanager20compose.repo.database.Database
import com.baymax104.bookmanager20compose.repo.database.dao
import com.baymax104.bookmanager20compose.repo.web.BookService
import com.baymax104.bookmanager20compose.repo.web.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository
 * @author John
 */
object BookRepo {

    private val bookService: BookService by service()

    private val bookDao: BookDao by dao()

    suspend fun requestBook(isbn: String): BookDto? = withContext(Dispatchers.IO) {
        val response = bookService.requestBook(isbn)
        if (response.errcode == 0) response.data else null
    }

    suspend fun queryAllProgressBook() = withContext(Dispatchers.IO) {
        bookDao.queryAllProcess()
    }

    suspend fun insertProgressBook(bookDto: BookDto) = Database.withTransaction {
//        bookMapper.insertProcess(book).let {
//            book.apply { id = it.toInt() }
//        }
    }

}