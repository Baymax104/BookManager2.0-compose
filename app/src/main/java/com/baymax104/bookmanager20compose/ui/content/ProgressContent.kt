package com.baymax104.bookmanager20compose.ui.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baymax104.bookmanager20compose.ui.navigation.Nav
import com.baymax104.bookmanager20compose.ui.theme.BookManagerFont
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

/**
 * 进度页
 * @author John
 */

@Composable
fun ProgressContent(
    appNavController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box {
            Text(
                text = "Hello",
            )
        }
    }
}

@Preview
@Composable
fun PreviewProgress() {
    val navController = rememberNavController()
    BookManagerTheme {
        ProgressContent(appNavController = navController)
    }
}