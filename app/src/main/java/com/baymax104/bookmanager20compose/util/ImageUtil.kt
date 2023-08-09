package com.baymax104.bookmanager20compose.util

import android.content.Context
import android.graphics.Bitmap
import com.blankj.utilcode.util.PathUtils
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.destination
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import java.io.File
import java.util.Date

/**
 *@Description
 *@Author John
 *@email
 *@Date 2023/3/21 23:25
 *@Version 1
 */
object ImageUtil {

    fun createFile(): File {
        val filename = "${Date().toDateString(detail = true)}.jpg"
        val parent = PathUtils.getExternalAppFilesPath()
        val file = File(parent, filename)
        file.createNewFile()
        return file
    }

    fun createCacheFile(): File {
        val filename = "${Date().toDateString(detail = true)}.jpg"
        val parent = PathUtils.getExternalAppCachePath()
        val file = File(parent, filename)
        file.createNewFile()
        return file
    }

    suspend fun compress(context: Context, file: File, dest: File): File {
        return Compressor.compress(context, file) {
            destination(dest)
            format(Bitmap.CompressFormat.JPEG)
            quality(80)
        }
    }
}