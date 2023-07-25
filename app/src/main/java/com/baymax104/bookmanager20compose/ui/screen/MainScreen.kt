package com.baymax104.bookmanager20compose.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.baymax104.bookmanager20compose.ui.components.BottomBar
import com.baymax104.bookmanager20compose.ui.components.Drawer
import com.baymax104.bookmanager20compose.ui.components.TopBar
import com.baymax104.bookmanager20compose.ui.navigation.MainHost
import com.baymax104.bookmanager20compose.ui.navigation.mainNavs
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.launch

/**
 * 应用布局框架
 * @author John
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    appNavController: NavController = rememberNavController()
) {
    val mainNavController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    Drawer(drawerState) {
        MainContent(
            appNavController = appNavController,
            mainNavController = mainNavController,
            onLeftNavClick = {
                scope.launch { drawerState.open() }
            },
            onActionClick = {
                ToastUtils.showShort("Action")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    appNavController: NavController,
    mainNavController: NavHostController,
    onLeftNavClick: () -> Unit,
    onActionClick: () -> Unit
) {
    Scaffold(
        topBar = { TopBar(onLeftNavClick, onActionClick) },
        bottomBar = { BottomBar(navController = mainNavController, navs = mainNavs) },
    ) { paddingValues ->
        MainHost(
            mainNavController,
            appNavController,
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