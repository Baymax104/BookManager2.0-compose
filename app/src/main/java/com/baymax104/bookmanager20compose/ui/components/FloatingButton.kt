package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

/**
 * 主页悬浮按钮
 * @param onClick 点击回调
 */
@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        shape = RoundedCornerShape(30),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 5.dp,
            focusedElevation = 10.dp,
            hoveredElevation = 10.dp,
            pressedElevation = 10.dp
        ),
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Icon(painter = painterResource(id = R.drawable.add), contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFloating() {
    BookManagerTheme {
        FloatingButton {}
    }
}