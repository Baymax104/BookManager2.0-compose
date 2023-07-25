package com.baymax104.bookmanager20compose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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