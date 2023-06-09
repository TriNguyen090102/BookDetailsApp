package com.example.bookappclonejetpackcompose.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bookappclonejetpackcompose.components.ItemBookList
import com.example.bookappclonejetpackcompose.components.TextInputView
import com.example.bookappclonejetpackcompose.model.BookItem
import com.example.bookappclonejetpackcompose.navigation.MainActions
import com.example.bookappclonejetpackcompose.ui.theme.text
import com.example.bookappclonejetpackcompose.utils.ViewState
import com.example.bookappclonejetpackcompose.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookListScreen(viewModel: MainViewModel, actions: MainActions) {
    val result = viewModel.books.value
    when (result) {
        ViewState.Loading -> Text(text = "Loading")
        is ViewState.Error -> Text(text = "Error found: ${result.exception}")
        is ViewState.Success -> {
            BookList(result.data, actions)
        }
        ViewState.Empty -> Text("No results found!")
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BookList(bookList: List<BookItem>, actions: MainActions) {
    val search = remember {
        mutableStateOf("")
    }
    val listState = rememberLazyListState()
    val scrollState = rememberScrollState()


    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp),
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {

        // title
        item {
            Text(
                text = "Explore thousands of \nbooks in go", style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Start,
                color = text,
                maxLines = 2,
                modifier = Modifier.padding(start = 16.dp, end = 24.dp, bottom = 24.dp)
            )
        }

        // search
        item {
            TextInputView(
                label = "Search book",
                value = search.value,
                onValueChanged = {
                    search.value = it
                })
        }

        // Search results title
        item {
            Text(
                text = "Famous books",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Start
            )
        }


        // All books list view
        items(
            //search book
            bookList.filter {
                it.title.contains(
                    search.value, ignoreCase = true
                )
            }) { book ->
            Log.d("books", "books are ${book.title}")

            ItemBookList(
                book.title,
                book.authors,
                book.isbn,
                book.thumbnailUrl,
                book.categories,
                actions
            )
        }
    }
}



