package com.baymax104.bookmanager20compose.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.baymax104.bookmanager20compose.repo.database.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


lateinit var MainScope: CoroutineScope

lateinit var MainScopeContext: CoroutineContext

fun mainLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = MainScope.launch(context, start, block)

fun ViewModel.viewModelLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context, start, block)

suspend fun <R> transaction(
    block: suspend () -> R
) = Database.withTransaction(block)
