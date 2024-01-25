package com.baymax104.bookmanager20compose.states

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * 系统相机状态
 * @author John
 * @since 2024/1/24
 */
@Stable
class CameraState {
    var photoFilePath by mutableStateOf("")
    var isShow by mutableStateOf(false)
}
