package com.example.bookappclonejetpackcompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.bookappclonejetpackcompose.ui.theme.primary
import com.example.bookappclonejetpackcompose.ui.theme.text
import com.example.bookappclonejetpackcompose.ui.theme.typography
import com.google.accompanist.flowlayout.FlowRow
import java.util.concurrent.Flow

@Composable
fun BookDetailsCard(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    categories: List<String>
) {

    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 16.dp, top = 40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.Transparent),

        contentAlignment = Alignment.Center
    ) {
        // Content
        BookImageContentView(title, authors, thumbnailUrl, categories)
    }


}

@Composable
fun BookImageContentView(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    categories: List<String>
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberImagePainter(data = thumbnailUrl),
            contentDescription = null,
            modifier = Modifier
                .size(240.dp, 140.dp)
                .padding(5.dp),

        )

        Spacer(modifier = Modifier.width(16.dp))
        // Description
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = authors.toString(), style = typography.caption, color = text.copy(0.7F))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, style = typography.subtitle1, color = text)
            Spacer(modifier = Modifier.height(12.dp))
            FlowRow() {
                categories.forEach {
                    ChipView(category = it)
                }
            }
        }


    }
}


@Preview
@Composable
fun bookCardPreview() {
    BookDetailsCard(
        "Practical Software Requirements",
        listOf("Benjamin L. Kovitz"),
        "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/kovitz.jpg",
        listOf("Software Engineering", "Theory")
    )
}