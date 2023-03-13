package com.example.bookappclonejetpackcompose.utils

import com.example.bookappclonejetpackcompose.model.BookItem
import java.lang.Exception

sealed class DetailViewState {
    object Empty : DetailViewState()
    object Loading : DetailViewState()
    data class Success(val data: BookItem) : DetailViewState()
    data class Error(val exception: Throwable) : DetailViewState()
}
