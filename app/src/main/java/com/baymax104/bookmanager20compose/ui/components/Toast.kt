package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme

/**
 * Toast by Compose
 * @author John
 * @since 2024/1/26
 */

@Composable
fun SuccessToast(
    message: String
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color(0xff00c853),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Rounded.Done,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = message,
                fontSize = 16.sp,
                color = Color.White,
                style = MaterialTheme.typography.displayMedium,
            )
        }
    }
}
@Composable
fun ErrorToast(
    message: String
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color(0xffd50000),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = message,
                fontSize = 16.sp,
                color = Color.White,
                style = MaterialTheme.typography.displayMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewToast() {
    BookManagerTheme {
        Column {
            SuccessToast("Hello")
//            ErrorToast(message = "Hello")
        }
    }
}

