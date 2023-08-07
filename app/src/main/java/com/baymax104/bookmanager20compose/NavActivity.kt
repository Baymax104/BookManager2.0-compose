package com.baymax104.bookmanager20compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.lifecycleScope
import com.baymax104.bookmanager20compose.ui.screen.NavGraphs
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.util.MainScope
import com.baymax104.bookmanager20compose.util.MainScopeContext
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import kotlinx.coroutines.cancel

/**
 * 主导航Activity
 */
class NavActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainScope = lifecycleScope
        MainScopeContext = MainScope.coroutineContext
        setContent {
            val engine = rememberAnimatedNavHostEngine(
                rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING
            )
            BookManagerTheme {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    engine = engine,
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MainScope.cancel()
    }
}
