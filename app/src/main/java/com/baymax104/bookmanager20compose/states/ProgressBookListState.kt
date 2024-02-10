package com.baymax104.bookmanager20compose.states

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baymax104.bookmanager20compose.bean.mapper.ProgressMapper
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import com.baymax104.bookmanager20compose.repo.BookRepo
import com.baymax104.bookmanager20compose.util.ImageUtils
import com.blankj.utilcode.util.Utils
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
            repo.queryAllProgressBooks()
                .map { ProgressMapper.entity2View(it) }
                .let { bookList.addAll(it) }
        }
    }

    suspend fun requestBook(isbn: String): Result<ProgressBookView> {
        return runCatching {
            val bookDto = repo.requestBook(isbn) ?: throw NullPointerException("图书请求失败")
            ProgressMapper.dto2View(bookDto)
        }
    }

    suspend fun addRequestBook(bookView: ProgressBookView) {
        val file = ImageUtils.download(Utils.getApp(), bookView.image)
        bookView.image = file.absolutePath

    }

    suspend fun addBook(bookView: ProgressBookView): Result<Unit> {
        return runCatching {
            val entity = ProgressMapper.view2Entity(bookView)
            val insertedBook = repo.insertProgressBook(entity)
            val view = ProgressMapper.entity2View(insertedBook)
            bookList.add(view)
        }
    }

}
