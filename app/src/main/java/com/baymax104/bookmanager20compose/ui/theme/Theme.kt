package com.baymax104.bookmanager20compose.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val lightColorScheme = lightColorScheme(
    primary = MainColor,
    onPrimary = Color.White,
    primaryContainer = Color.White,
    secondary = SecondColor,
    onSecondary = Color.White,
    secondaryContainer = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = MainColorShallow,
    outline = MainColor,
    outlineVariant = MainColorVariant,
)

@Composable
fun BookManagerTheme(
    isDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            systemUiController.setStatusBarColor(Color.Transparent, isDarkTheme)
        }
    }

    MaterialTheme(
        colorScheme = lightColorScheme,
        typography = Typography,
        content = content
    )
}