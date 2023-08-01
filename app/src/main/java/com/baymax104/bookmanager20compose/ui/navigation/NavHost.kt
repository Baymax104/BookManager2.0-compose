package com.baymax104.bookmanager20compose.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.baymax104.bookmanager20compose.ui.screen.FinishContent
import com.baymax104.bookmanager20compose.ui.screen.MainScreen
import com.baymax104.bookmanager20compose.ui.screen.ProgressScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

/**
 * APP导航Host
 * @param appNavController APP导航HostController
 * @param modifier modifier
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppHost(
    modifier: Modifier = Modifier,
    appNavController: NavHostController = LocalAppNav.current
) {
    AnimatedNavHost(
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
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = LocalMainNav.current
) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = Nav.Progress.route,
        modifier = modifier
    ) {
        composable(
            route = Nav.Progress.route,
            enterTransition = { slideInHorizontally { -it } },
            exitTransition = { slideOutHorizontally { -it } }
        ) {
            ProgressScreen()
        }
        composable(
            route = Nav.Finish.route,
            enterTransition = { slideInHorizontally { it } },
            exitTransition = { slideOutHorizontally { it } }
        ) {
            FinishContent()
        }

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