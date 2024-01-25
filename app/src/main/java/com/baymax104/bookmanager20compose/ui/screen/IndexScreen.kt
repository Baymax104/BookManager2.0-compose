package com.baymax104.bookmanager20compose.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.bean.dto.BookDto
import com.baymax104.bookmanager20compose.states.ProgressBookListState
import com.baymax104.bookmanager20compose.ui.components.BottomBar
import com.baymax104.bookmanager20compose.ui.components.Drawer
import com.baymax104.bookmanager20compose.ui.components.IndexTransition
import com.baymax104.bookmanager20compose.ui.components.TopBar
import com.baymax104.bookmanager20compose.ui.screen.destinations.ManualAddScreenDestination
import com.baymax104.bookmanager20compose.ui.screen.destinations.ScanScreenDestination
//import com.baymax104.bookmanager20compose.ui.screen.destinations.ManualAddSheetDestination
//import com.baymax104.bookmanager20compose.ui.screen.destinations.ScanScreenDestination
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.blankj.utilcode.util.ToastUtils
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import kotlinx.coroutines.launch


/**
 * 主页
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@RootNavGraph(start = true)
@Destination(style = IndexTransition::class)
@Composable
fun IndexScreen(
    navigator: DestinationsNavigator,
    scanRecipient: ResultRecipient<ScanScreenDestination, String>,
    manualAddRecipient: ResultRecipient<ManualAddScreenDestination, BookDto>
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val progressBookListState: ProgressBookListState = viewModel()

    scanRecipient.onNavResult {
        when (it) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                scope.launch {
                    val book = progressBookListState.requestBook(it.value)
                    if (book != null) {
                        // TODO 展示
                        ToastUtils.showShort("展示")
                    } else {
                        ToastUtils.showShort("请求失败")
                    }
                }
            }
        }
    }
    Drawer(drawerState) {
        IndexContent(
            onLeftNavClick = {
                scope.launch {
                    drawerState.open()
                }
            },
            onActionClick = {
                ToastUtils.showShort("Action")
            },
            pages = listOf(IndexPage.Progress, IndexPage.Finish)
        ) {
            when (it) {
                0 -> ProgressScreen(navigator, manualAddRecipient)
                1 -> FinishScreen()
            }
        }
    }
}

/**
 * 主页框架
 * @param onLeftNavClick 左侧导航按钮回调
 * @param onActionClick 右侧行为按钮回调
 */
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
private fun IndexContent(
    onLeftNavClick: () -> Unit,
    onActionClick: () -> Unit,
    pages: List<IndexPage>,
    pageContent: @Composable (PagerScope.(Int) -> Unit)
) {
    val pagerState = rememberPagerState { pages.size }
    Scaffold(
        topBar = { TopBar(onLeftNavClick, onActionClick) },
        bottomBar = { BottomBar(pagerState = pagerState, indexPages = pages) },
    ) { paddingValues ->
        HorizontalPager(
            modifier = Modifier.padding(paddingValues),
            state = pagerState,
            key = { pages[it].key },
            pageContent = pageContent
        )
    }
}

sealed class IndexPage(
    val key: String,
    val label: String,
    val icon: Int
) {
    object Progress : IndexPage("progress", "进度", R.drawable.progress)
    object Finish: IndexPage("finish", "读过", R.drawable.finish)
}

@Preview
@Composable
fun Preview() {
    BookManagerTheme {
        IndexScreen(
            EmptyDestinationsNavigator,
            EmptyResultRecipient(),
            EmptyResultRecipient()
        )
    }
}