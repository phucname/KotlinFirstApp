package com.example.myfisrtapp.Common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object TextFieldStyles {
    val defaultColors
        @Composable
        get() = TextFieldDefaults.colors(
            focusedTextColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = Color.White,
            disabledIndicatorColor = Color.Transparent
        )
}
data class TextFieldStyleManager(
    val focusedTextColor: Color = Color.Gray,
    val focusedIndicatorColor: Color = Color.Transparent,
    val unfocusedIndicatorColor: Color = Color.Transparent,

    val cursorColor: Color = Color.White,
    val disabledIndicatorColor: Color = Color.Transparent
) {
    @Composable
    fun textFieldColors() = TextFieldDefaults.colors(
        focusedTextColor = focusedTextColor,
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        cursorColor = cursorColor,
        disabledIndicatorColor = disabledIndicatorColor
    )
}

