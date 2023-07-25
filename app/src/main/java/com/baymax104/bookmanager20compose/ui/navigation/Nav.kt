package com.baymax104.bookmanager20compose.ui.navigation

import com.baymax104.bookmanager20compose.R

/**
 * 导航
 * @author John
 */

sealed class Nav(
    val route: String,
    val label: String,
    val icon: Int
) {
    object Main : Nav("/main", "主页", 0)
    object Progress : Nav("/main/progress", "进度", R.drawable.progress)
    object Finish: Nav("/main/finish", "读过", R.drawable.finish)
}

val mainNavs = listOf(
    Nav.Progress,
    Nav.Finish
)
