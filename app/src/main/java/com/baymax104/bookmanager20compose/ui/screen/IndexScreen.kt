@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
package com.baymax104.bookmanager20compose.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import com.baymax104.bookmanager20compose.ui.components.BottomBar
import com.baymax104.bookmanager20compose.ui.components.Drawer
import com.baymax104.bookmanager20compose.ui.components.LeftInTransition
import com.baymax104.bookmanager20compose.ui.components.TopBar
import com.baymax104.bookmanager20compose.ui.screen.destinations.FinishEditScreenDestination
import com.baymax104.bookmanager20compose.ui.screen.destinations.InfoScreenDestination
import com.baymax104.bookmanager20compose.ui.screen.destinations.ManualAddScreenDestination
import com.baymax104.bookmanager20compose.ui.screen.destinations.ProgressEditScreenDestination
import com.baymax104.bookmanager20compose.ui.screen.destinations.ScanScreenDestination
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.result.EmptyResultRecipient
import com.ramcosta.composedestinations.result.ResultRecipient
import kotlinx.coroutines.launch


/**
 * 主页
 */
@RootNavGraph(start = true)
@Destination(style = LeftInTransition::class)
@Composable
fun IndexScreen(
    navigator: DestinationsNavigator,
    scanRecipient: ResultRecipient<ScanScreenDestination, String>,
    manualAddRecipient: ResultRecipient<ManualAddScreenDestination, ProgressBookView>,
    infoRecipient: ResultRecipient<InfoScreenDestination, ProgressBookView>
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val pages = listOf(IndexPage.Progress, IndexPage.Finish)
    val pagerState = rememberPagerState { pages.size }

    Drawer(drawerState) {
        IndexContent(
            onLeftNavClick = {
                scope.launch {
                    drawerState.open()
                }
            },
            onActionClick = {
                when (pagerState.currentPage) {
                    0 -> navigator.navigate(ProgressEditScreenDestination)
                    1 -> navigator.navigate(FinishEditScreenDestination)
                }
            },
            pages = pages,
            pagerState = pagerState
        ) {
            when (it) {
                0 -> ProgressScreen(
                    navigator,
                    manualAddRecipient,
                    scanRecipient,
                    infoRecipient
                )
                1 -> FinishScreen(

                )
            }
        }
    }
}

/**
 * 主页框架
 * @param onLeftNavClick 左侧导航按钮回调
 * @param onActionClick 右侧行为按钮回调
 */
@Composable
private fun IndexContent(
    onLeftNavClick: () -> Unit,
    onActionClick: () -> Unit,
    pagerState: PagerState,
    pages: List<IndexPage>,
    pageContent: @Composable (PagerScope.(Int) -> Unit)
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.app_name),
                navigationIcon = R.drawable.left_menu,
                navigationClick = onLeftNavClick,
                actionIcon = R.drawable.edit,
                actionClick = onActionClick
            )
        },
        bottomBar = {
            BottomBar(
                pagerState = pagerState,
                indexPages = pages
            )
        },
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
            EmptyResultRecipient(),
            EmptyResultRecipient()
        )
    }
}