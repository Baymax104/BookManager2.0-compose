package com.baymax104.bookmanager20compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import kotlinx.coroutines.launch

/**
 * 侧边栏
 * @author John
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            DrawerSheet {
                DrawerHeader { scope.launch { drawerState.close() } }
                DrawerButton(label = "导出", icon = R.drawable.ic_export)
            }
        },
        drawerState = drawerState,
        gesturesEnabled = false,
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerSheet(
    content: @Composable ColumnScope.() -> Unit
) {
    ModalDrawerSheet(
        content = content,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        drawerTonalElevation = 0.dp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerButton(
    label: String,
    icon: Int,
    onClick: () -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(100),
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 15.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = label)
        }
    }
}

@Composable
private fun DrawerHeader(onCloseClick: () -> Unit) {
    Surface(
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier.padding(start = 15.dp)
            )
            IconButton(onClick = onCloseClick) {
                Icon(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewDrawer() {
    BookManagerTheme {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        Drawer(drawerState) {}
//        DrawerHeader()
    }
}
