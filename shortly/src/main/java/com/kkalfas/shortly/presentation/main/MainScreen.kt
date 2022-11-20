package com.kkalfas.shortly.presentation.main

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
            isLandscape = false,
            state = HistoryUiState(),
            onUrlChanged = {},
            onPrimaryButtonClick = {},
            onDeleteAction = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 812, heightDp = 375)
@Composable
private fun PreviewMainContentLandscape() {
    ShortlyTheme {
        MainContent(
            modifier = Modifier.background(MaterialTheme.colors.background),
            isLandscape = true,
            state = HistoryUiState(),
            onUrlChanged = {},
            onPrimaryButtonClick = {},
            onDeleteAction = {}
        )
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel()
) {
    val stateFlow by viewModel.state.collectAsState()
    if (stateFlow.errorMessage != -1) {
        val message = stringResource(id = stateFlow.errorMessage)
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.clearError()
    }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    Scaffold(
        drawerGesturesEnabled = false,
        content = { innerPadding ->
            MainContent(
                modifier = modifier
                    .padding(innerPadding)
                    .navigationBarsPadding(),
                isLandscape = isLandscape,
                state = stateFlow,
                onUrlChanged = viewModel::onUrlChanged,
                onPrimaryButtonClick = viewModel::onShortenUrl,
                onDeleteAction = viewModel::onDeleteLink
            )
        }
    )
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    state: HistoryUiState,
    onUrlChanged: (String) -> Unit,
    onPrimaryButtonClick: () -> Unit,
    onDeleteAction: (String) -> Unit
) {
    if (isLandscape) InLandscape(
        modifier = modifier,
        state = state,
        onUrlChanged = onUrlChanged,
        onPrimaryButtonClick = onPrimaryButtonClick,
        onDeleteAction = onDeleteAction
    )
    else InPortrait(
        modifier = modifier,
        state = state,
        onUrlChanged = onUrlChanged,
        onPrimaryButtonClick = onPrimaryButtonClick,
        onDeleteAction = onDeleteAction
    )
}

@Composable
private fun InPortrait(
    modifier: Modifier,
    state: HistoryUiState,
    onUrlChanged: (String) -> Unit,
    onPrimaryButtonClick: () -> Unit,
    onDeleteAction: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundSecondary)
    ) {
        // takes 75% of visible space
        HistoryContent(
            modifier = Modifier.fillMaxHeight(.75f),
            state = state,
            onDeleteAction = onDeleteAction
        )
        // takes 25% of visible space
        FooterContent(
            modifier = Modifier.fillMaxSize(),
            inputValue = state.urlInput,
            isInputError = state.isInputError,
            onInputChanged = onUrlChanged,
            onPrimaryButtonClick = onPrimaryButtonClick
        )
    }
}

@Composable
private fun InLandscape(
    modifier: Modifier,
    state: HistoryUiState,
    onUrlChanged: (String) -> Unit,
    onPrimaryButtonClick: () -> Unit,
    onDeleteAction: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundSecondary),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HistoryContent(
            modifier = Modifier.fillMaxWidth(.5f),
            state = state,
            onDeleteAction = onDeleteAction
        )
        FooterContent(
            modifier = Modifier.fillMaxSize(),
            inputValue = state.urlInput,
            isInputError = state.isInputError,
            onInputChanged = onUrlChanged,
            onPrimaryButtonClick = onPrimaryButtonClick
        )
    }
}
