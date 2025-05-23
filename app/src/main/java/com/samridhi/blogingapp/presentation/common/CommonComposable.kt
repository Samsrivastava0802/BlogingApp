package com.samridhi.blogingapp.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.samridhi.blogingapp.ui.theme.errorColor
import com.samridhi.blogingapp.ui.theme.subTitle

@Composable
fun ErrorMessage(
    errorMessage: String,
    error: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage,
            color = if (error) errorColor else Color.Black,
            style = MaterialTheme.typography.subTitle
        )
    }
}