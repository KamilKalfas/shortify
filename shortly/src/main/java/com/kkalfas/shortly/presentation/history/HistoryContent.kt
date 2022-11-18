package com.kkalfas.shortly.presentation.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkalfas.shortly.R
import com.kkalfas.shortly.presentation.components.text.Body1
import com.kkalfas.shortly.presentation.components.text.Header6
import com.kkalfas.shortly.presentation.theme.backgroundSecondary

@Preview(showSystemUi = true)
@Composable
private fun PreviewEmptyHistoryContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundSecondary)) {
        HistoryContent(modifier = Modifier.fillMaxHeight(.75f))
    }
}

@Composable
fun HistoryContent(
    modifier: Modifier,
    history: List<String> = emptyList()
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (history.isEmpty()) GetStarted()
        else LinkHistory()
    }
}

@Composable
private fun LinkHistory(
    modifier: Modifier = Modifier
) {
    // TODO
}

@Composable
private fun GetStarted() {
    Spacer(modifier = Modifier.height(18.dp))
    Image(
        painter = painterResource(id = R.drawable.title_logo),
        contentDescription = "logo"
    )
    Spacer(modifier = Modifier.padding(top = 8.dp))
    Image(
        painter = painterResource(id = R.drawable.ic_illustration),
        contentDescription = "illustration"
    )
    Header6(text = stringResource(id = R.string.main_body_header))
    Body1(
        modifier = Modifier.padding(horizontal = 75.dp),
        text = stringResource(id = R.string.main_body)
    )
}
