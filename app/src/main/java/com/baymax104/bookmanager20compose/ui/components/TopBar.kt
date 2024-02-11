@file:OptIn(ExperimentalMaterial3Api::class)

package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.ui.theme.ContainerColor

/**
 * 只带返回键标题栏
 * @author John
 * @since 2024/2/11
 */
@Composable
fun TopBar(
    title: String,
    navigationIcon: Int? = null,
    navigationClick: () -> Unit = {},
    actionIcon: Int? = null,
    actionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (navigationIcon != null) {
                ActionButton(id = navigationIcon, onClick = navigationClick)
            }
        },
        actions = {
            if (actionIcon != null) {
                ActionButton(id = actionIcon, onClick = actionClick)
            }
        },
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = ContainerColor,
            actionIconContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary
        )
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
        TopBar("Hello") {}
    }
}