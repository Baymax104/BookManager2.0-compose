package com.baymax104.bookmanager20compose.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * ViewModel作用域
 * @author John
 */

object ApplicationViewModelStore : ViewModelStoreOwner {
    override val viewModelStore: ViewModelStore = ViewModelStore()
}

@Composable
inline fun <reified VM : ViewModel> rememberApplicationViewModel(): VM =
    viewModel(
        modelClass = VM::class.java,
        viewModelStoreOwner = ApplicationViewModelStore
    )

@Composable
inline fun <reified VM : ViewModel> rememberAdjoinViewModel(): VM =
    viewModel(modelClass = VM::class.java)
