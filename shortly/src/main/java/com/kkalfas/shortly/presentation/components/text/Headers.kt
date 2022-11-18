package com.kkalfas.shortly.presentation.components.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kkalfas.shortly.presentation.theme.ShortlyTheme

@Composable
fun Header6(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colors.onBackground
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.h6,
        color = textColor
    )
}

@Preview
@Composable
private fun PreviewHeader() {
    ShortlyTheme {
        Header6(text = "Shortly")
    }
}
