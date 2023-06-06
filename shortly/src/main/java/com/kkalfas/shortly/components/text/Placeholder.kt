package com.kkalfas.shortly.components.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kkalfas.shortly.R
import com.kkalfas.shortly.theme.ShortlyTheme
import com.kkalfas.shortly.theme.lightGray

@Composable
fun Placeholder(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colors.lightGray
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        color = textColor
    )
}

@Preview
@Composable
private fun PreviewPlaceholder() {
    ShortlyTheme {
        Body1(text = stringResource(id = R.string.footer_input_hint))
    }
}
