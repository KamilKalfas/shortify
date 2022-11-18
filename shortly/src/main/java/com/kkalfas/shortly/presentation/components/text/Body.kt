package com.kkalfas.shortly.presentation.components.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kkalfas.shortly.R
import com.kkalfas.shortly.presentation.theme.ShortlyTheme

@Composable
fun Body1(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colors.onBackground
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
private fun PreviewBody1() {
    ShortlyTheme {
        Body1(text = stringResource(id = R.string.main_body))
    }
}
