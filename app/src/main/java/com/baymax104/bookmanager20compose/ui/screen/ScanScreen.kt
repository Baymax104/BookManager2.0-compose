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
import com.baymax104.bookmanager20compose.ui.components.ScanTransition
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.google.zxing.BarcodeFormat
import com.google.zxing.DecodeHintType
import com.king.zxing.DecodeConfig
import com.king.zxing.DefaultCameraScan
import com.king.zxing.ViewfinderView
import com.king.zxing.analyze.MultiFormatAnalyzer
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator


/**
 * 扫码页
 */
@RootNavGraph
@Destination(style = ScanTransition::class)
@Composable
fun ScanScreen(
    resultNavigator: ResultBackNavigator<String>
) {
    val context = LocalContext.current as FragmentActivity
    val preview = PreviewView(context)
    val scanner = remember {
        CameraScanner(context, preview).apply {
            setOnResultCallback { resultNavigator.navigateBack(result = it) }
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


/**
 * 扫码扫描器
 *
 * @constructor Create empty Floating menu scope
 *
 * @param context [FragmentActivity]
 * @param previewView [PreviewView]
 */
class CameraScanner(
    context: FragmentActivity,
    previewView: PreviewView
) {
    private val scanner = DefaultCameraScan(context, previewView)

    private val isbnReg =
        Regex("^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}\$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}\$|97[89][0-9]{10}\$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}\$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]\$")

    init {
        val config = DecodeConfig().apply {
            hints = mapOf(
                DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.EAN_13),
                DecodeHintType.TRY_HARDER to BarcodeFormat.EAN_13,
                DecodeHintType.CHARACTER_SET to "UTF-8"
            )
        }
        scanner.apply {
            setAnalyzer(MultiFormatAnalyzer(config))
            setNeedAutoZoom(true)
            setDarkLightLux(35f)
            setBrightLightLux(40f)
            setOnScanResultCallback { true }
        }
    }

    fun setOnResultCallback(callback: (String) -> Unit) {
        scanner.setOnScanResultCallback { result ->
            result.text.takeIf { isbnReg.matches(it) }?.let { callback(it) }
            true
        }
    }

    fun start() = scanner.startCamera()

    fun release() = scanner.release()
}

@Preview(showBackground = true)
@Composable
fun PreviewScan() {
    BookManagerTheme {
        ScanScreen(EmptyResultBackNavigator())
    }
}