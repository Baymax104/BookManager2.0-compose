package com.baymax104.bookmanager20compose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.base.rememberAdjoinViewModel
import com.baymax104.bookmanager20compose.base.rememberApplicationViewModel
import com.baymax104.bookmanager20compose.entity.Book
import com.baymax104.bookmanager20compose.request.ProgressRequester
import com.baymax104.bookmanager20compose.states.ProgressStateHolder
import com.baymax104.bookmanager20compose.ui.components.AddDialog
import com.baymax104.bookmanager20compose.ui.components.FloatingButtonMenu
import com.baymax104.bookmanager20compose.ui.components.ProgressItem
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.blankj.utilcode.util.ToastUtils
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun ProgressScreen() {
    val stateHolder: ProgressStateHolder = rememberAdjoinViewModel()
    val requester: ProgressRequester = rememberApplicationViewModel()
    LaunchedEffect(Unit) {
        stateHolder.bookListFlow.emit(requester.queryProgressBook())
    }
    ProgressContent(states = stateHolder)
}

/**
 * 进度页
 */
@Composable
private fun ProgressContent(states: ProgressStateHolder) {
    val bookListState by states.bookListFlow.collectAsState()
    val dialogState = rememberMaterialDialogState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        ProgressListContent(bookListState)
//        FloatingButton(
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(end = 20.dp, bottom = 25.dp)
//        ) {
//            dialogState.show()
//        }
        FloatingButtonMenu(
            size = 60.dp,
            icon = R.drawable.add,
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 25.dp)
        ) {
            item(
                icon = R.drawable.add,
                label = { Text(text = "Hello", style = MaterialTheme.typography.labelSmall) },
            ) {

            }
            item(
                icon = R.drawable.add,
                label = { Text(text = "Hello", style = MaterialTheme.typography.labelSmall) },
            ) {

            }
        }
        AddDialog(dialogState) {
            ToastUtils.showShort("$it")
        }
    }
}

/**
 * 进度列表页
 */
@Composable
private fun ProgressListContent(books: List<Book>) {
    if (books.isEmpty()) {
        Image(painter = painterResource(id = R.drawable.no_data), contentDescription = null)
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 15.dp, horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = books,
                key = { it.id },
                contentType = { it.id }
            ) {
                ProgressItem(it)
            }
        }
    }
}

@Preview
@Composable
fun PreviewProgress() {
    BookManagerTheme {
        ProgressScreen()
    }
}