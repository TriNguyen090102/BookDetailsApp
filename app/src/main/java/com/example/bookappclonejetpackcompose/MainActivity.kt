package com.example.bookappclonejetpackcompose

import android.content.ClipData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookappclonejetpackcompose.components.BookDetailsCard
import com.example.bookappclonejetpackcompose.components.ItemBookList
import com.example.bookappclonejetpackcompose.components.TextInputView
import com.example.bookappclonejetpackcompose.components.TopBar
import com.example.bookappclonejetpackcompose.navigation.NavGraph

import com.example.bookappclonejetpackcompose.ui.theme.BookAppCloneJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BookAppCloneJetpackComposeTheme {

    }
}