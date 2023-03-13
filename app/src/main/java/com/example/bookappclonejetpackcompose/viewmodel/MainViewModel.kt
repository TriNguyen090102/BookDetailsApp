package com.example.bookappclonejetpackcompose.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookappclonejetpackcompose.model.BookItem
import com.example.bookappclonejetpackcompose.utils.DetailViewState
import com.example.bookappclonejetpackcompose.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val detailViewState = MutableStateFlow<DetailViewState>(DetailViewState.Loading)

    val books = viewState.asStateFlow()
    val bookDetails = detailViewState.asStateFlow()


    val format: Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    fun getAllBooks(context: Context) {
        viewModelScope.launch {
            try {
                val myJsonBookList =
                    context.assets.open("books.json").bufferedReader().use { it.readText() }

                val bookList = format.decodeFromString<List<BookItem>>(myJsonBookList)
                viewState.value = ViewState.Success(bookList)
            } catch (e: Exception) {

                viewState.value = ViewState.Error(e)
            }
        }


    }

    fun getBookById(context: Context, isbn: String) {
        viewModelScope.launch {
            try {
                val myJsonBookList =
                    context.assets.open("books.json").bufferedReader().use { it.readText() }

                val bookList = format.decodeFromString<List<BookItem>>(myJsonBookList)
                val matchingBook = bookList.firstOrNull { it.isbn == isbn }
                if (matchingBook != null) {
                    detailViewState.value = DetailViewState.Success(matchingBook)
                    Log.d("success","success")
                } else {
                    detailViewState.value = DetailViewState.Empty
                    Log.d("fail","empty")
                }
            } catch (e: Exception) {
                detailViewState.value = DetailViewState.Error(e)
                Log.d("fail","error")
            }
        }
    }
}

