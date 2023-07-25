package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

/**
 * 应用标题栏
 * @author John
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onLeftNavClick: () -> Unit,
    onActionClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "BookManager", style = MaterialTheme.typography.titleLarge) },
        navigationIcon = { ActionButton(R.drawable.left_nav, onLeftNavClick) },
        actions = { ActionButton(R.drawable.edit, onActionClick) },
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    )
}

@Composable
fun ActionButton(id: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    BookManagerTheme {
        TopBar({}, {})
    }
}
