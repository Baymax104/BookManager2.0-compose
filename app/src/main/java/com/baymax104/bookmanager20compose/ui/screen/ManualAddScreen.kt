@file:OptIn(ExperimentalMaterial3Api::class)
package com.baymax104.bookmanager20compose.ui.screen


import android.Manifest
import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import coil.compose.AsyncImage
import com.baymax104.bookmanager20compose.R
import com.baymax104.bookmanager20compose.bean.vo.ProgressBookView
import com.baymax104.bookmanager20compose.states.CameraState
import com.baymax104.bookmanager20compose.ui.components.SelectionHeader
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.util.ImageUtil
import com.baymax104.bookmanager20compose.util.requestPermission
import com.blankj.utilcode.util.IntentUtils
import com.blankj.utilcode.util.UriUtils
import com.hjq.toast.Toaster
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet
import kotlinx.coroutines.launch
import java.io.File

/**
 * 手动录入底部弹窗
 * @author John
 * @since 2023/8/7
 */
@Destination(style = DestinationStyleBottomSheet::class)
@Composable
fun ManualAddScreen(
    navigator: ResultBackNavigator<ProgressBookView>
) {
    val titleState = remember { mutableStateOf("") }
    val authorState = remember { mutableStateOf("") }
    val pageState = remember { mutableStateOf("") }
    val cameraState = remember { CameraState() }
    ManualAddSheetContent(
        titleState = titleState,
        authorState = authorState,
        pageState = pageState,
        cameraState = cameraState,
        onConfirm = {
            if (pageState.value.isDigitsOnly()) {
                val titleValue = titleState.value.takeIf { it.isNotEmpty() } ?: "未填写"
                val authorValue = authorState.value.takeIf { it.isNotEmpty() } ?: "佚名"
                val pageValue =
                    pageState.value.takeIf { it.isNotEmpty() && it.isDigitsOnly() }?.toInt() ?: 0
                if (pageValue > 1) {
                    ProgressBookView().apply {
                        title = titleValue
                        author = authorValue
                        page = pageValue
                    }.let {
                        navigator.navigateBack(it)
                    }
                } else {
                    Toaster.showShort("页数必须大于1")
                }
            } else {
                Toaster.showShort("页数格式错误")
            }
        },
        onCancel = {
            navigator.navigateBack()
        }
    )
}

@Composable
private fun ManualAddSheetContent(
    titleState: MutableState<String>,
    authorState: MutableState<String>,
    pageState: MutableState<String>,
    cameraState: CameraState,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectionHeader(
            title = "添加图书",
            onConfirm = onConfirm,
            onCancel = onCancel
        )
        Divider(color = MaterialTheme.colorScheme.primary)
        CameraCard(
            cameraState = cameraState,
            modifier = Modifier
                .size(width = 130.dp, height = 180.dp)
                .padding(vertical = 10.dp)
        )
        InfoField(
            label = "书名",
            state = titleState,
            modifier = Modifier
                .padding(bottom = 3.dp)
                .height(60.dp)
        )
        InfoField(
            label = "作者",
            state = authorState,
            modifier = Modifier
                .padding(vertical = 3.dp)
                .height(60.dp)
        )
        InfoField(
            label = "总页数",
            state = pageState,
            modifier = Modifier
                .padding(top = 3.dp, bottom = 20.dp)
                .height(60.dp),
            inputType = KeyboardType.Number,
            isError = !pageState.value.isDigitsOnly() ||
                    (pageState.value.isNotEmpty() && pageState.value.toInt() <= 1),
        )
    }
}

@Composable
private fun InfoField(
    label: String,
    state: MutableState<String>,
    modifier: Modifier = Modifier,
    inputType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
        keyboardOptions = KeyboardOptions(keyboardType = inputType),
        shape = RoundedCornerShape(15.dp),
        modifier = modifier,
        singleLine = true,
        isError = isError,
        textStyle = MaterialTheme.typography.displayMedium
    )
}


@Composable
private fun CameraCard(
    cameraState: CameraState,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            scope.launch {
                val file = File(cameraState.photoFilePath)
                val dest = ImageUtil.createFile()
                val compressed = ImageUtil.compress(context, file, dest)
                cameraState.apply {
                    photoFilePath = compressed.absolutePath
                    isShow = true
                }
            }
        }
    }
    ElevatedCard(
        modifier = modifier,
        onClick = {
            requestPermission(Manifest.permission.CAMERA) {
                granted {
                    val file = ImageUtil.createCacheFile()
                    cameraState.photoFilePath = file.absolutePath
                    val uri = UriUtils.file2Uri(file)
                    val intent = IntentUtils.getCaptureIntent(uri)
                    launcher.launch(intent)
                }
                denied {
                    Toaster.showShort("权限申请失败，请到权限中心开启权限")
                }
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (cameraState.isShow) {
                AsyncImage(
                    model = cameraState.photoFilePath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.camera)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewManualSheet() {
    BookManagerTheme {
        ManualAddScreen(navigator = EmptyResultBackNavigator())
    }
}