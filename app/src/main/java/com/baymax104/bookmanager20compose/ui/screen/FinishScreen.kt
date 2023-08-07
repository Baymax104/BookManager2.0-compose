package com.baymax104.bookmanager20compose.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 完成页
 * @author John
 */
@Composable
fun FinishScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello Finish")

    }
}