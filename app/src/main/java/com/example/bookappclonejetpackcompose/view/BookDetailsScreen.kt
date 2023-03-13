package com.example.bookappclonejetpackcompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bookappclonejetpackcompose.components.BookDetailsCard
import com.example.bookappclonejetpackcompose.components.TopBar
import com.example.bookappclonejetpackcompose.navigation.MainActions
import com.example.bookappclonejetpackcompose.ui.theme.text
import com.example.bookappclonejetpackcompose.utils.DetailViewState
import com.example.bookappclonejetpackcompose.utils.ViewState
import com.example.bookappclonejetpackcompose.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookDetailsScreen(viewModel: MainViewModel, actions: MainActions) {
    Scaffold(topBar = {
        TopBar(
            title = "Go back",
            action = actions
        )
    }) {
        BookDetails(viewModel)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookDetails(viewModel: MainViewModel) {
    val result = viewModel.bookDetails.value
    when (result) {
        DetailViewState.Loading -> Text(text = "Loading")
        is DetailViewState.Error -> Text(text = "Error found: ${result.exception}")
        is DetailViewState.Success -> {
            val book = result.data
            LazyColumn {
                // Book Details Card
                item {
                    BookDetailsCard(book.title, book.authors, book.thumbnailUrl, book.categories)
                }

                item{
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Book Details",
                        style = typography.subtitle1,
                        color = text,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = book.longDescription,
                        style = typography.body1,
                        textAlign = TextAlign.Justify,
                        color = text,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                }
            }
        }
        DetailViewState.Empty -> Text("No results found!")


    }
}