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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.baymax104.bookmanager20compose.entity.Book
import com.baymax104.bookmanager20compose.ui.components.SelectionHeader
import com.baymax104.bookmanager20compose.ui.theme.BookManagerTheme
import com.baymax104.bookmanager20compose.util.ImageUtil
import com.baymax104.bookmanager20compose.util.requestPermission
import com.blankj.utilcode.util.IntentUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.UriUtils
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
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

@RootNavGraph
@Destination(style = DestinationStyleBottomSheet::class)
@Composable
fun ManualAddSheet(
    navigator: ResultBackNavigator<Book>
) {
    val titleState = remember { mutableStateOf("") }
    val authorState = remember { mutableStateOf("") }
    val pageState = remember { mutableStateOf("") }
    ManualAddSheetContent(
        titleState = titleState,
        authorState = authorState,
        pageState = pageState,
        onConfirm = {
            if (!pageState.value.isDigitsOnly()) {
                ToastUtils.showShort("页数格式错误")
            } else {
                val pageValue =
                    pageState.value.takeIf { it.isNotEmpty() && it.isDigitsOnly() }?.toInt() ?: 0
                if (pageValue <= 1) {
                    ToastUtils.showShort("页数必须大于1")
                } else {
                    val book = Book().apply {
                        title = titleState.value.takeIf { it.isNotEmpty() } ?: "未填写"
                        author = authorState.value.takeIf { it.isNotEmpty() } ?: "佚名"
                        page = pageValue
                    }
                    navigator.navigateBack(book)
                }
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
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    val camera = remember { CameraState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            scope.launch {
                val file = File(camera.photoFilePath)
                val dest = ImageUtil.createFile()
                val compressed = ImageUtil.compress(context, file, dest)
                with(camera) {
                    photoFilePath = compressed.absolutePath
                    isShow = true
                }
            }
        }
    }

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
            modifier = Modifier
                .size(width = 130.dp, height = 180.dp)
                .padding(vertical = 10.dp),
            cameraState = camera
        ) {
            requestPermission(Manifest.permission.CAMERA) {
                granted {
                    val file = ImageUtil.createCacheFile()
                    camera.photoFilePath = file.absolutePath
                    val uri = UriUtils.file2Uri(file)
                    val intent = IntentUtils.getCaptureIntent(uri)
                    launcher.launch(intent)
                }
                denied { ToastUtils.showShort("权限申请失败，请到权限中心开启权限") }
            }
        }
        InfoField(
            label = "书名",
            state = titleState,
            onValueChange = { titleState.value = it },
            modifier = Modifier
                .padding(bottom = 3.dp)
                .height(60.dp)
        )
        InfoField(
            label = "作者",
            state = authorState,
            onValueChange = { authorState.value = it },
            modifier = Modifier
                .padding(vertical = 3.dp)
                .height(60.dp)
        )
        InfoField(
            label = "总页数",
            state = pageState,
            onValueChange = { pageState.value = it },
            modifier = Modifier
                .padding(top = 3.dp, bottom = 20.dp)
                .height(60.dp),
            inputType = KeyboardType.Number,
            isError = !pageState.value.isDigitsOnly()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InfoField(
    label: String,
    state: MutableState<String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    inputType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = onValueChange,
        label = { Text(text = label, style = MaterialTheme.typography.labelSmall) },
        keyboardOptions = KeyboardOptions(keyboardType = inputType),
        shape = RoundedCornerShape(15.dp),
        modifier = modifier,
        singleLine = true,
        isError = isError,
        textStyle = MaterialTheme.typography.displayMedium
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CameraCard(
    modifier: Modifier = Modifier,
    cameraState: CameraState,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
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

@Stable
class CameraState {
    var photoFilePath by mutableStateOf("")
    var isShow by mutableStateOf(false)
}

@Preview(showBackground = true)
@Composable
fun PreviewManualSheet() {
    BookManagerTheme {
        ManualAddSheet(navigator = EmptyResultBackNavigator())
    }
}