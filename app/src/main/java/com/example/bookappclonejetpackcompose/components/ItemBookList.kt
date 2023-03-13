package com.example.bookappclonejetpackcompose.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.bookappclonejetpackcompose.navigation.MainActions
import com.example.bookappclonejetpackcompose.ui.theme.background
import com.example.bookappclonejetpackcompose.ui.theme.primary
import com.example.bookappclonejetpackcompose.ui.theme.text
import com.example.bookappclonejetpackcompose.ui.theme.typography
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun ItemBookList(
    title: String,
    authors: List<String>,
    isbn: String,
    thumbnailUrl: String,
    categories: List<String>,
    actions: MainActions
) {
    Card(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
            .clickable {
                Log.d("click","clicked")
                actions.gotoBookDetails.invoke(isbn)
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = rememberImagePainter(data = thumbnailUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(98.dp, 145.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Inside
            )

            Spacer(modifier = Modifier.width(16.dp))
            // Description
            Column() {
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
}

@Composable
fun ChipView(category: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(primary.copy(.10F))
            .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category, style = typography.caption, color = primary)
    }
}

