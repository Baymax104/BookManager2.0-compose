package com.baymax104.bookmanager20compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.baymax104.bookmanager20compose.ui.content.FinishContent
import com.baymax104.bookmanager20compose.ui.content.MainContent
import com.baymax104.bookmanager20compose.ui.content.ProgressContent

/**
 * 导航Host
 * @author John
 */

@Composable
fun AppHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Nav.Main.route,
        modifier = modifier
    ) {
        composable(Nav.Main.route) { MainContent() }
    }
}

@Composable
fun MainHost(
    navHostController: NavHostController,
    appNavController: NavController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Nav.Progress.route,
        modifier = modifier
    ) {
        composable(Nav.Progress.route) { ProgressContent(appNavController) }
        composable(Nav.Finish.route) { FinishContent(appNavController) }
    }
}