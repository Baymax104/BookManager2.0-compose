package com.baymax104.bookmanager20compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.lifecycleScope
import com.baymax104.bookmanager20compose.ui.navigation.AppHost
import com.baymax104.bookmanager20compose.ui.navigation.LocalAppNav
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.util.MainScope
import com.baymax104.bookmanager20compose.util.MainScopeContext
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.cancel

/**
 * 主导航Activity
 */
class NavActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainScope = lifecycleScope
        MainScopeContext = MainScope.coroutineContext
        setContent {
            val appNavController = rememberAnimatedNavController()
            CompositionLocalProvider(LocalAppNav provides appNavController) {
                BookManagerTheme {
                    AppHost()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MainScope.cancel()
    }
}
