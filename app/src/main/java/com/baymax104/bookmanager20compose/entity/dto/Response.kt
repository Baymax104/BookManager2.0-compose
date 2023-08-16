package com.baymax104.bookmanager20compose.entity.dto

import kotlinx.serialization.Serializable

/**
 * 请求响应实体
 * @author John
 */
@Serializable
data class Response<E>(
    val errcode: Int,
    val errmsg: String,
    val data: E? = null
)
