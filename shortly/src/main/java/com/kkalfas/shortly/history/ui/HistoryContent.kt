package com.kkalfas.shortly.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkalfas.shortly.R
import com.kkalfas.shortly.components.text.Body1
import com.kkalfas.shortly.data.history.model.LinkEntryModel
import com.kkalfas.shortly.theme.ShortlyTheme
import com.kkalfas.shortly.theme.backgroundSecondary

@Preview(showSystemUi = true)
@Composable
private fun PreviewHistory() {
    val linkModel = LinkEntryModel(
        code = "d78tda",
        short = "https://short.link/d78tda",
        original = "https://wow.com/such/a/long/url/much/wow"
    )
    ShortlyTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.backgroundSecondary)
        ) {
            HistoryContent(
                modifier = Modifier.fillMaxHeight(.75f),
                state = HistoryUiState(
                    urlInput = "",
                    history = listOf(linkModel, linkModel, linkModel)
                ),
                onDeleteAction = {}
            )
        }
    }
}

@Composable
fun HistoryContent(
    modifier: Modifier,
    state: HistoryUiState,
    onDeleteAction: (String) -> Unit
) {
    Column(
        modifier = modifier.statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.history.isEmpty()) GetStarted()
        else LinkHistory(
            history = state.history,
            onDeleteAction = onDeleteAction
        )
    }
}

@Composable
private fun LinkHistory(
    history: List<LinkEntryModel>,
    onDeleteAction: (String) -> Unit
) {
    Spacer(modifier = Modifier.height(4.dp))
    Body1(text = stringResource(id = R.string.history_title))
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 25.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(history) { item ->
            LinkCard(
                linkEntity = item,
                onDeleteAction = onDeleteAction
            )
        }
    }
}
