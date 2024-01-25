package com.baymax104.bookmanager20compose.states

import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Stable
import androidx.fragment.app.FragmentActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.DecodeHintType
import com.google.zxing.Result
import com.king.camera.scan.BaseCameraScan
import com.king.camera.scan.config.CameraConfigFactory
import com.king.zxing.DecodeConfig
import com.king.zxing.analyze.MultiFormatAnalyzer

/**
 * 扫码扫描器
 *
 * @constructor Create empty Floating menu scope
 *
 * @param context [FragmentActivity]
 * @param previewView [PreviewView]
 */
@Stable
class ScannerState(
    context: FragmentActivity,
    previewView: PreviewView,
    resultCallback: (String) -> Unit
) {
    private val scanner = BaseCameraScan<Result>(context, previewView)

    private val isbnReg =
        Regex("^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}\$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}\$|97[89][0-9]{10}\$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}\$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]\$")

    init {
        val analyzer = DecodeConfig().apply {
            hints = mapOf(
                DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.EAN_13),
                DecodeHintType.TRY_HARDER to BarcodeFormat.EAN_13,
                DecodeHintType.CHARACTER_SET to "UTF-8"
            )
        }.let {
            MultiFormatAnalyzer(it)
        }
        val config = CameraConfigFactory.createDefaultCameraConfig(context, CameraSelector.LENS_FACING_BACK)
        scanner.apply {
            setAnalyzer(analyzer)
            setCameraConfig(config)
            setOnScanResultCallback { res ->
                res.result.text.takeIf { isbnReg.matches(it) }?.let { resultCallback(it) }
            }
        }
    }

    fun start() = scanner.startCamera()

    fun release() = scanner.release()
}
