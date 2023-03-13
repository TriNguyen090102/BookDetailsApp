package com.example.bookappclonejetpackcompose.utils

import android.view.View
import com.example.bookappclonejetpackcompose.model.BookItem
import kotlinx.coroutines.flow.MutableStateFlow

sealed class ViewState {
    object Empty : ViewState()
    object Loading : ViewState()
    data class Success(val data: List<BookItem>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}
