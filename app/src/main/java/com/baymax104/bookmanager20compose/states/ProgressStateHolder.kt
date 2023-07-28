package com.baymax104.bookmanager20compose.states

import androidx.lifecycle.ViewModel
import com.baymax104.bookmanager20compose.entity.Book
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * 进度页状态
 * @author John
 */
class ProgressStateHolder : ViewModel() {
    val bookListFlow: MutableStateFlow<List<Book>> = MutableStateFlow(mutableListOf())
}
