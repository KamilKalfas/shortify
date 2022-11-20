package com.kkalfas.shortly.presentation.main

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            state = HistoryUiState(),
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
    if (stateFlow.errorMessage != -1) {
        val message = stringResource(id = stateFlow.errorMessage)
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.clearError()
    }
    Scaffold(
        drawerGesturesEnabled = false,
        content = { innerPadding ->
            MainContent(
                modifier = modifier
                    .padding(innerPadding)
                    .navigationBarsPadding(),
                state = stateFlow,
                onUrlChanged = viewModel::onUrlChanged,
                onPrimaryButtonClick = viewModel::onShortenUrl
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
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            && configuration.screenWidthDp > 550

    if (isLandscape) InLandscape(
        modifier = modifier,
        state = state,
        onUrlChanged = onUrlChanged,
        onPrimaryButtonClick = onPrimaryButtonClick
    )
    else InPortrait(
        modifier = modifier,
        state = state,
        onUrlChanged = onUrlChanged,
        onPrimaryButtonClick = onPrimaryButtonClick
    )
}

@Composable
private fun InPortrait(
    modifier: Modifier,
    state: HistoryUiState,
    onUrlChanged: (String) -> Unit,
    onPrimaryButtonClick: () -> Unit
) {
    Column(
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
    onPrimaryButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.backgroundSecondary)
    ) {
        HistoryContent(
            modifier = Modifier.wrapContentHeight(),
            state = state
        )
        FooterContent(
            modifier = Modifier.heightIn(min = 245.dp),
            isLandscape = true,
            inputValue = state.urlInput,
            isInputError = state.isInputError,
            onInputChanged = onUrlChanged,
            onPrimaryButtonClick = onPrimaryButtonClick
        )
    }
}
