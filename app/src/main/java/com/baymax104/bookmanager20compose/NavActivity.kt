package com.baymax104.bookmanager20compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.baymax104.bookmanager20compose.ui.navigation.AppHost
import com.baymax104.bookmanager20compose.ui.navigation.LocalAppNav
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

/**
 * 主导航Activity
 */
class NavActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appNavController = rememberNavController()
            CompositionLocalProvider(LocalAppNav provides appNavController) {
                BookManagerTheme {
                    AppHost()
                }
            }
        }
    }
}
