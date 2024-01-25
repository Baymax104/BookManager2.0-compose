package com.baymax104.bookmanager20compose.states

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baymax104.bookmanager20compose.bean.dto.BookDto
import com.baymax104.bookmanager20compose.bean.mapper.ProgressMapper
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import com.baymax104.bookmanager20compose.repo.BookRepo
import kotlinx.coroutines.launch

/**
 * 图书列表状态
 * @author John
 */
@Stable
class ProgressBookListState(
    val repo: BookRepo = BookRepo
) : ViewModel() {

    val bookList = mutableStateListOf<ProgressBookView>()

    init {
        viewModelScope.launch {
//            bookList.addAll(repo.queryAllProgressBook())
        }
    }

    suspend fun requestBook(isbn: String): ProgressBookView? {
        val bookDto = repo.requestBook(isbn) ?: return null
        return ProgressMapper.dto2View(bookDto)
    }

    suspend fun insertBook(bookDto: BookDto) {
//        bookDto.apply {
//            startTime = Date()
//            tableRank = bookList.size
//        }
//        val insertedBook = repo.insertProgressBook(bookDto)
//        bookList.add(insertedBook)
    }


}
