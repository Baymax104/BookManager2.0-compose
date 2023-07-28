package com.baymax104.bookmanager20compose.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.baymax104.bookmanager20compose.ui.components.BottomBar
import com.baymax104.bookmanager20compose.ui.components.Drawer
import com.baymax104.bookmanager20compose.ui.components.TopBar
import com.baymax104.bookmanager20compose.ui.navigation.LocalMainNav
import com.baymax104.bookmanager20compose.ui.navigation.MainHost
import com.baymax104.bookmanager20compose.ui.navigation.mainNavs
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.launch


/**
 * 主页
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val mainNavController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (drawerState.isOpen) {
        BackHandler {
            scope.launch { drawerState.close() }
        }
    }

    CompositionLocalProvider(LocalMainNav provides mainNavController) {
        Drawer(drawerState) {
            MainContent(
                onLeftNavClick = {
                    scope.launch { drawerState.open() }
                },
                onActionClick = {
                    ToastUtils.showShort("Action")
                }
            )
        }
    }
}

/**
 * 主页框架
 * @param onLeftNavClick 左侧导航按钮回调
 * @param onActionClick 右侧行为按钮回调
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    onLeftNavClick: () -> Unit,
    onActionClick: () -> Unit
) {
    Scaffold(
        topBar = { TopBar(onLeftNavClick, onActionClick) },
        bottomBar = { BottomBar(navs = mainNavs) },
    ) { paddingValues ->
        MainHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}


@Preview
@Composable
fun Preview() {
    BookManagerTheme {
        MainScreen()
    }
}