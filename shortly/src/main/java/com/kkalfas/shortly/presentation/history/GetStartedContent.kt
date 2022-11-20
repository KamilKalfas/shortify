package com.kkalfas.shortly.presentation.history

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
private fun PreviewGetStarted() {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundSecondary)) {
        HistoryContent(
            modifier = Modifier.fillMaxHeight(.75f),
            state = HistoryUiState(),
            onDeleteAction = {}
        )
    }
}

@Composable
fun GetStarted() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    Spacer(modifier = Modifier.height(18.dp))
    Image(
        painter = painterResource(id = R.drawable.title_logo),
        contentDescription = "logo"
    )
    Spacer(modifier = Modifier.padding(top = 8.dp))
    Image(
        modifier = Modifier.heightIn(max = if(isLandscape) 162.dp else 324.dp),
                painter = painterResource (id = R.drawable.ic_illustration),
        contentDescription = "illustration",
        contentScale = ContentScale.Inside
    )
    Header6(text = stringResource(id = R.string.getting_started_body_header))
    Body1(
        modifier = Modifier.padding(horizontal = 75.dp),
        text = stringResource(id = R.string.getting_started_body)
    )
}
