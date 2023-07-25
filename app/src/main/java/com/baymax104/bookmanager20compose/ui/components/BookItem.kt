package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.baymax104.bookmanager20compose.entity.Book
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

/**
 * 主页列表项
 * @author John
 */

@Composable
fun ProgressItem(book: Book) {
    val bookState by remember { mutableStateOf(book) }
}

@Preview
@Composable
fun PreviewProgressItem() {
    BookManagerTheme {
        ProgressItem(Book())
    }
}