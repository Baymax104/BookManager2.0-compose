package com.baymax104.bookmanager20compose.states

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baymax104.bookmanager20compose.entity.Book
import com.baymax104.bookmanager20compose.repo.Repository
import kotlinx.coroutines.launch
import java.util.Date

/**
 * 图书列表状态
 * @author John
 */
@Stable
class BookStateHolder(
    val repo: Repository = Repository
) : ViewModel() {

    val bookList: SnapshotStateList<Book> = mutableStateListOf()
    init {
        viewModelScope.launch {
            bookList.addAll(repo.queryAllProgressBook())
        }
    }

    suspend fun requestBook(isbn: String): Book? {
        return repo.requestBook(isbn)
    }

    fun insertBook(book: Book) {
        viewModelScope.launch {
            book.apply {
                startTime = Date()
                tableRank = bookList.size
            }
            val insertedBook = repo.insertProgressBook(book)
            bookList.add(insertedBook)
        }
    }


}
