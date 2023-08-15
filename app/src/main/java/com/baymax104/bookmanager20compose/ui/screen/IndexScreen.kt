package com.baymax104.bookmanager20compose.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.entity.Book
import com.baymax104.bookmanager20compose.ui.components.BottomBar
import com.baymax104.bookmanager20compose.ui.components.Drawer
import com.baymax104.bookmanager20compose.ui.components.IndexTransition
import com.baymax104.bookmanager20compose.ui.components.TopBar
import com.baymax104.bookmanager20compose.ui.screen.destinations.ManualAddSheetDestination
import com.baymax104.bookmanager20compose.ui.screen.destinations.ScanScreenDestination
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
@OptIn(
    ExperimentalMaterial3Api::class,
)
@RootNavGraph(start = true)
@Destination(style = IndexTransition::class)
@Composable
fun IndexScreen(
    navigator: DestinationsNavigator,
    scanRecipient: ResultRecipient<ScanScreenDestination, String>,
    manualAddRecipient: ResultRecipient<ManualAddSheetDestination, Book>
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    scanRecipient.onNavResult {
        when (it) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                ToastUtils.showShort(it.value)
            }
        }
    }
    if (drawerState.isOpen) {
        BackHandler {
            scope.launch { drawerState.close() }
        }
    }
    Drawer(drawerState) {
        IndexContent(
            navigator = navigator,
            manualAddRecipient = manualAddRecipient,
            onLeftNavClick = {
                scope.launch { drawerState.open() }
            },
            onActionClick = {
                ToastUtils.showShort("Action")
            }
        )
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
    navigator: DestinationsNavigator,
    manualAddRecipient: ResultRecipient<ManualAddSheetDestination, Book>
) {
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = { TopBar(onLeftNavClick, onActionClick) },
        bottomBar = { BottomBar(pagerState = pagerState, indexPages = indexPages) },
    ) { paddingValues ->
        HorizontalPager(
            pageCount = 2,
            modifier = Modifier.padding(paddingValues),
            state = pagerState,
            key = { indexPages[it].key }
        ) {
            when (it) {
                0 -> ProgressScreen(navigator, manualAddRecipient)
                1 -> FinishScreen()
            }
        }
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

val indexPages = listOf(
    IndexPage.Progress,
    IndexPage.Finish
)


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