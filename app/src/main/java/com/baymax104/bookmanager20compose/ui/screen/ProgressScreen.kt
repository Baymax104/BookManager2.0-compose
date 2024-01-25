package com.baymax104.bookmanager20compose.ui.screen

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.bean.dto.BookDto
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import com.baymax104.bookmanager20compose.states.ProgressBookListState
import com.baymax104.bookmanager20compose.ui.components.FloatingMenu
import com.baymax104.bookmanager20compose.ui.components.ProgressItem
import com.baymax104.bookmanager20compose.ui.screen.destinations.ManualAddScreenDestination
import com.baymax104.bookmanager20compose.ui.screen.destinations.ScanScreenDestination
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.util.requestPermission
import com.blankj.utilcode.util.ToastUtils
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import kotlinx.coroutines.launch

@Composable
fun ProgressScreen(
    navigator: DestinationsNavigator,
    manualAddRecipient: ResultRecipient<ManualAddScreenDestination, BookDto>
) {
    val stateHolder: ProgressBookListState = viewModel()
    val bookListState = remember { stateHolder.bookList }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    manualAddRecipient.onNavResult {
        when (it) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                scope.launch {
                    stateHolder.insertBook(it.value)
                    listState.animateScrollToItem(bookListState.lastIndex)
                }
            }
        }
    }
    ProgressContent(
        books = bookListState,
        lazyListState = listState,
        scanClick = {
            requestPermission(Manifest.permission.CAMERA) {
                granted { navigator.navigate(ScanScreenDestination) }
                denied { ToastUtils.showShort("权限申请失败，请到权限中心开启权限") }
            }
        },
        inputClick = {
            navigator.navigate(ManualAddScreenDestination)
        }
    )
}

/**
 * 进度页
 */
@Composable
private fun ProgressContent(
    books: List<ProgressBookView>,
    lazyListState: LazyListState,
    scanClick: () -> Unit,
    inputClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        ProgressListContent(
            lazyListState = lazyListState,
            books = books
        )
        FloatingMenu(
            size = 60.dp,
            icon = R.drawable.add,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 25.dp),
        ) {
            item(icon = R.drawable.scan, onClick = scanClick)
            item(icon = R.drawable.input, onClick = inputClick)
        }
    }
}

/**
 * 进度列表页
 */
@Composable
private fun ProgressListContent(
    lazyListState: LazyListState,
    books: List<ProgressBookView>
) {
    if (books.isEmpty()) {
        Image(painter = painterResource(id = R.drawable.no_data), contentDescription = null)
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 15.dp, horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            state = lazyListState
        ) {
            items(
                items = books,
                key = { it.id },
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
        ProgressScreen(
            navigator = EmptyDestinationsNavigator,
            manualAddRecipient = EmptyResultRecipient()
        )
    }
}