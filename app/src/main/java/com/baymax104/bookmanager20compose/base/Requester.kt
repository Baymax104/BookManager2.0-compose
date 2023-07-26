package com.baymax104.bookmanager20compose.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * Requester请求组件
 */
open class Requester : ViewModel() {
    class ResultCallback<T> {
        private var onSuccess: (T) -> Unit = {}
        private var onFail: (Throwable) -> Unit = {}

        companion object {
            inline fun <T> build(block: ResultCallback<T>.() -> Unit) =
                ResultCallback<T>().apply(block)
        }

        fun success(callback: (T) -> Unit) = apply { onSuccess = callback }
        fun fail(callback: (Throwable) -> Unit) = apply { onFail = callback }
        operator fun invoke(result: Result<T>) {
            when {
                result.isSuccess -> result.onSuccess(onSuccess)
                result.isFailure -> result.onFailure(onFail)
            }
        }

        suspend inline fun runCoroutine(crossinline action: suspend CoroutineScope.() -> T) =
            coroutineScope {
                runCatching {
                    action()
                }.let { invoke(it) }
            }
    }

}
