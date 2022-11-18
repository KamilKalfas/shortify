package com.kkalfas.shortly.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kkalfas.shortly.presentation.components.FooterContent
import com.kkalfas.shortly.presentation.history.HistoryContent
import com.kkalfas.shortly.presentation.history.HistoryUiState
import com.kkalfas.shortly.presentation.history.HistoryViewModel
import com.kkalfas.shortly.presentation.theme.ShortlyTheme
import com.kkalfas.shortly.presentation.theme.backgroundSecondary

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewMainContent() {
    ShortlyTheme {
        MainContent(
            modifier = Modifier.background(MaterialTheme.colors.background),
            state = HistoryUiState(""),
            onUrlChanged = {},
            onPrimaryButtonClick = {}
        )
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel()
) {
    val stateFlow by viewModel.state.collectAsState()
    Scaffold(
        drawerGesturesEnabled = false,
        content = { innerPadding ->
            MainContent(
                modifier = modifier
                    .padding(innerPadding)
                    .navigationBarsPadding(),
                state = stateFlow,
                onUrlChanged = viewModel::onUrlChanged,
                onPrimaryButtonClick = {
                    //TODO
                }
            )
        }
    )
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    state: HistoryUiState,
    onUrlChanged: (String) -> Unit,
    onPrimaryButtonClick: () -> Unit
) = Column(
    modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.backgroundSecondary)
) {
    // takes 75% of visible space
    HistoryContent(
        modifier = Modifier.fillMaxHeight(.75f),
        state = state
    )
    // takes 25% of visible space
    FooterContent(
        inputValue = state.urlInput,
        onInputChanged = onUrlChanged,
        onPrimaryButtonClick = onPrimaryButtonClick
    )
}
