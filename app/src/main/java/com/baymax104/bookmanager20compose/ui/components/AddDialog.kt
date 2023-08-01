package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.listItems
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

/**
 * Progress页添加对话框
 * @author John
 */

@Composable
fun AddDialog(
    dialogState: MaterialDialogState = rememberMaterialDialogState(),
    onItemClick: (Int) -> Unit = {}
) {
    MaterialDialog(
        dialogState = dialogState,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    ) {
        DialogScaffold(
            title = {
                Text(
                    text = "录入方式",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .wrapContentSize(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        ) {
            listItems(
                list = listOf("扫码录入", "手动录入"),
                onClick = { index, _ ->
                    onItemClick(index)
                }
            ) { _, item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 7.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun DialogScaffold(
    title: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        title()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        content()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddDialog() {
    BookManagerTheme {
        val dialogState = rememberMaterialDialogState(true)
        AddDialog(dialogState)
    }
}