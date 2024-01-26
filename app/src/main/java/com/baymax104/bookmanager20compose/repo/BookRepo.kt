package com.baymax104.bookmanager20compose.repo

import com.baymax104.bookmanager20compose.bean.dto.BookDto
import com.baymax104.bookmanager20compose.bean.entity.BookEntity
import com.baymax104.bookmanager20compose.repo.database.BookDao
import com.baymax104.bookmanager20compose.repo.database.dao
import com.baymax104.bookmanager20compose.repo.database.transaction
import com.baymax104.bookmanager20compose.repo.web.BookService
import com.baymax104.bookmanager20compose.repo.web.io
import com.baymax104.bookmanager20compose.repo.web.service

/**
 * Repository
 * @author John
 */
object BookRepo {

    private val bookService: BookService by service()

    private val bookDao: BookDao by dao()

    suspend fun requestBook(isbn: String): BookDto? = io {
        val response = bookService.requestBook(isbn)
        if (response.errcode == 0) response.data else null
    }

    suspend fun queryAllProgressBooks() = io {
        bookDao.queryAllProgress()
    }

    suspend fun insertProgressBook(book: BookEntity) = transaction {
        bookDao.countProgress().let {
            book.rank = it + 1
        }
        bookDao.insertProgress(book).let {
            book.id = it.toInt()
        }
        book
    }

}