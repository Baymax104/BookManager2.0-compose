@file:OptIn(ExperimentalMaterial3Api::class)

package com.baymax104.bookmanager20compose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import com.baymax104.bookmanager20compose.states.ProgressBookListState
import com.baymax104.bookmanager20compose.ui.components.ProgressItem
import com.baymax104.bookmanager20compose.ui.components.RightInTransition
import com.baymax104.bookmanager20compose.ui.components.TopBar
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

/**
 * 进度图书编辑页
 * @author John
 * @since 2024/2/11
 */
@Destination(style = RightInTransition::class)
@Composable
fun ProgressEditScreen(
    navigator: DestinationsNavigator
) {
    val bookListState: ProgressBookListState = viewModel()
    val listState = rememberLazyListState()

    ProgressEditContent(
        books = bookListState.bookList,
        lazyListState = listState,
        backClick = {
            navigator.navigateUp()
        }
    )
}

@Composable
private fun ProgressEditContent(
    books: List<ProgressBookView>,
    lazyListState: LazyListState,
    backClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "编辑列表",
                navigationIcon = R.drawable.back,
                navigationClick = backClick
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            EditListContent(
                books = books,
                lazyListState = lazyListState,
            )
        }
    }
}

@Composable
private fun EditListContent(
    books: List<ProgressBookView>,
    lazyListState: LazyListState,
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
                ProgressItem(it) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEdit() {
    BookManagerTheme {
        ProgressEditScreen(
            navigator = EmptyDestinationsNavigator
        )
    }
}