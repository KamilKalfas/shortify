package com.kkalfas.shortly.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.kkalfas.shortly.R
import com.kkalfas.shortly.presentation.components.text.InputField

// takes 25% of visible space
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Footer(
    modifier: Modifier = Modifier,
    onPrimaryButtonClick: () -> Unit
) {
    var url by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardActions = KeyboardActions(
        onNext = { focusManager.moveFocus(FocusDirection.Down) },
        onDone = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
            .paint(
                painterResource(id = R.drawable.ic_shape),
                alignment = Alignment.TopEnd
            )
            .padding(horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InputField(
            value = url,
            onValueChange = { value ->
                // TODO validation
                url = value
            },
            hint = stringResource(id = R.string.main_input_hint),
            keyboardActions = keyboardActions
        )
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.main_button_shorten_it),
            onClick = onPrimaryButtonClick
        )
    }
}
