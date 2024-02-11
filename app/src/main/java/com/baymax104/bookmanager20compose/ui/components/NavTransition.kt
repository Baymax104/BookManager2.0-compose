package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

/**
 * NavigationTransition
 * @author John
 * @since 2023/8/7
 */
sealed class NavTransition(
    val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition,
    val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
) : DestinationStyle.Animated {

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        return enter()
    }

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        return exit()
    }
}

object LeftInTransition : NavTransition(
    enter = {
        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) +
                fadeIn(animationSpec = tween(400))
    },
    exit = {
        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) +
                fadeOut(animationSpec = tween(400))
    }
)

object RightInTransition : NavTransition(
    enter = {
        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) +
                fadeIn(animationSpec = tween(400))
    },
    exit = {
        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) +
                fadeOut(animationSpec = tween(400))
    }
)
