package com.baymax104.bookmanager20compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.baymax104.bookmanager20compose.ui.screen.NavGraphs
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.util.MainScope
import com.baymax104.bookmanager20compose.util.MainScopeContext
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import kotlinx.coroutines.cancel

/**
 * 主导航Activity
 */
class NavActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class,
        ExperimentalMaterialApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainScope = lifecycleScope
        MainScopeContext = MainScope.coroutineContext
        setContent {
            val engine = rememberAnimatedNavHostEngine(
                rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING
            )
            val bottomNavigator = rememberBottomSheetNavigator()
            val navigator = rememberAnimatedNavController().apply {
                navigatorProvider.addNavigator(bottomNavigator)
            }
            BookManagerTheme {
                ModalBottomSheetLayout(
                    bottomSheetNavigator = bottomNavigator,
                    sheetShape = RoundedCornerShape(20.dp),
                    sheetContentColor = MaterialTheme.colorScheme.primary
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        engine = engine,
                        navController = navigator
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialNavigationApi::class)
    @Composable
    private fun rememberBottomSheetNavigator(): BottomSheetNavigator {
        val bottomSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )
        return remember { BottomSheetNavigator(bottomSheetState) }
    }

    override fun onDestroy() {
        super.onDestroy()
        MainScope.cancel()
    }
}
