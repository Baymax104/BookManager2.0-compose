package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

/**
 * Progress页悬浮按钮
 * @author John
 */
@Composable
fun FloatingButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        shape = RoundedCornerShape(100),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 10.dp
        ),
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(painter = painterResource(id = R.drawable.add), contentDescription = null)
    }
}

@Preview
@Composable
fun PreviewFloating() {
    BookManagerTheme {
        FloatingButton {}
    }
}