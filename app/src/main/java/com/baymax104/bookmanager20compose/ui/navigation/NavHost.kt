package com.baymax104.bookmanager20compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.baymax104.bookmanager20compose.ui.screen.FinishContent
import com.baymax104.bookmanager20compose.ui.screen.MainScreen
import com.baymax104.bookmanager20compose.ui.screen.ProgressContent

/**
 * APP导航Host
 * @param appNavController APP导航HostController
 * @param modifier modifier
 */
@Composable
fun AppHost(
    modifier: Modifier = Modifier,
    appNavController: NavHostController = LocalAppNav.current
) {
    NavHost(
        navController = appNavController,
        startDestination = Nav.Main.route,
        modifier = modifier
    ) {
        composable(Nav.Main.route) { MainScreen() }
    }
}

/**
 * 主页导航Host
 * @param navHostController 主页导航HostController
 * @param modifier modifier
 */
@Composable
fun MainHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = LocalMainNav.current
) {
    NavHost(
        navController = navHostController,
        startDestination = Nav.Progress.route,
        modifier = modifier
    ) {
        composable(Nav.Progress.route) { ProgressContent() }
        composable(Nav.Finish.route) { FinishContent() }
    }
}

/**
 * 全局APP导航NavController
 */
val LocalAppNav = staticCompositionLocalOf<NavHostController> {
    error("CompositionLocal LocalAppNav not present")
}

/**
 * 主页导航NavController
 */
val LocalMainNav = staticCompositionLocalOf<NavHostController> {
    error("CompositionLocal LocalMainNav not present")
}