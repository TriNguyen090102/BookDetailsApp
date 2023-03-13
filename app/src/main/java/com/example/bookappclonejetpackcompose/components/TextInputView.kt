package com.example.bookappclonejetpackcompose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookappclonejetpackcompose.ui.theme.text
import com.example.bookappclonejetpackcompose.ui.theme.typography

@Composable
fun LabelView(label: String) {
    Text(
        text = label,
        style = typography.caption,
        color = text.copy(0.7F)
    )
}

@ExperimentalComposeUiApi
@Composable
fun TextInputView(label: String, value: String, onValueChanged: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, top = 20.dp),
        label = { LabelView(label = label) },
        value = value,
        onValueChange = {
            onValueChanged(it)
        },
        textStyle = typography.body1,
        colors = textFieldColors(),
        keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )
}

@Composable
fun textFieldColors() = TextFieldDefaults.textFieldColors(

    textColor = text.copy(0.7F),
    focusedLabelColor = text.copy(0.7F),
    focusedIndicatorColor = text.copy(0.7F),
    unfocusedIndicatorColor = Color.LightGray,
    cursorColor = text.copy(0.7F),
    placeholderColor = colors.onSurface,
    disabledPlaceholderColor = colors.onSurface
)