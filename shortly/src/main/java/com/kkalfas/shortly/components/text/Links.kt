package com.kkalfas.shortly.components.text

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkalfas.shortly.theme.ShortlyTheme

@Composable
fun LinkCardText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onBackground,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
fun ShortLinkCardText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.primary,
        maxLines = 1
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewLinkCardText() {
    ShortlyTheme {
        Surface(Modifier.wrapContentHeight()) {
            LinkCardText(modifier = Modifier.padding(horizontal = 80.dp), text = "http://somewhere.on/the/intra/webz")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewShortLinkCardText() {
    ShortlyTheme {
        Surface(Modifier.wrapContentHeight()) {
            ShortLinkCardText(text = "http://its.so/sh0rt")
        }
    }
}
