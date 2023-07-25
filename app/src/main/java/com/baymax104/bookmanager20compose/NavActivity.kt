package com.baymax104.bookmanager20compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.baymax104.bookmanager20compose.ui.navigation.AppHost
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

class NavActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            BookManagerTheme {
                AppHost(navHostController = navHostController)
            }
        }
    }
}
