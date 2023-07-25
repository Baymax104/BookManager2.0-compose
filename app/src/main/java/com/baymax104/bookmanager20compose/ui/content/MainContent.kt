package com.baymax104.bookmanager20compose.ui.content

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baymax104.bookmanager20compose.ui.components.BottomBar
import com.baymax104.bookmanager20compose.ui.components.TopBar
import com.baymax104.bookmanager20compose.ui.navigation.MainHost
import com.baymax104.bookmanager20compose.ui.navigation.mainNavs
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

/**
 * 应用布局框架
 * @author John
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    appNavController: NavController = rememberNavController()
) {
    val mainNavController = rememberNavController()

    Scaffold(
        topBar = { TopBar({}, {}) },
        bottomBar = { BottomBar(navController = mainNavController, navs = mainNavs) },
    ) { paddingValues ->
        MainHost(
            mainNavController,
            appNavController,
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        )
    }

}


@Preview
@Composable
fun Preview() {
    BookManagerTheme {
        MainContent()
    }
}