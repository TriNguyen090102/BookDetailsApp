package com.example.bookappclonejetpackcompose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookappclonejetpackcompose.navigation.MainActions
import com.example.bookappclonejetpackcompose.ui.theme.typography

//@Composable
//fun TopBar(title:String){
//    Row(modifier = Modifier
//        .fillMaxWidth()
//        .padding(start = 16.dp, top = 16.dp, end = 16.dp),horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
//        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
//        Spacer(modifier = Modifier.width(12.dp))
//        Text(text = title, style = typography.h5, color = MaterialTheme.colors.primaryVariant)
//
//    }
//}
@Composable
fun TopBar(title: String, action: MainActions) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .clickable { action.upPress },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "",
            modifier = Modifier.clickable(onClick = action.upPress)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = title, style = typography.h5, color = MaterialTheme.colors.primaryVariant)

    }
}