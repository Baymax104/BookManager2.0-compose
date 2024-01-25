package com.baymax104.bookmanager20compose.ui.screen

import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.states.ScannerState
import com.baymax104.bookmanager20compose.ui.components.ScanTransition
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.king.view.viewfinderview.ViewfinderView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator


/**
 * 扫码页
 */
@Destination(style = ScanTransition::class)
@Composable
fun ScanScreen(
    resultNavigator: ResultBackNavigator<String>
) {
    val context = LocalContext.current as FragmentActivity
    val preview = PreviewView(context)
    val scanner = remember {
        ScannerState(context, preview) {
            resultNavigator.navigateBack(result = it)
        }
    }
    DisposableEffect(scanner) {
        scanner.start()
        onDispose { scanner.release() }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { preview },
            modifier = Modifier.fillMaxSize()
        )
        AndroidView(
            factory = {
                ViewfinderView(it).apply {
                    setLabelText("请将图书背面的条形码放入扫描框中")
                    setLaserStyle(ViewfinderView.LaserStyle.GRID)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = {
                resultNavigator.navigateBack()
            },
            modifier = Modifier
                .statusBarsPadding()
                .padding(start = 20.dp, top = 15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewScan() {
    BookManagerTheme {
        ScanScreen(EmptyResultBackNavigator())
    }
}