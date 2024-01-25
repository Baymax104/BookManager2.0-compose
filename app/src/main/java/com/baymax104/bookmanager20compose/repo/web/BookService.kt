package com.baymax104.bookmanager20compose.repo.web

import com.baymax104.bookmanager20compose.bean.dto.BookDto
import com.baymax104.bookmanager20compose.bean.dto.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Book网络服务
 * @author John
 */
interface BookService {

    @GET("/v1/book/isbn")
    suspend fun requestBook(@Query("isbn") isbn: String): Response<BookDto>
}