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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FooterContent(
    modifier: Modifier = Modifier,
    isLandscape: Boolean = false,
    inputValue: String,
    onInputChanged: (String) -> Unit,
    onPrimaryButtonClick: () -> Unit,
) {
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
            .padding(horizontal = if (isLandscape) 120.dp else 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InputField(
            value = TextFieldValue(inputValue),
            onValueChange = { value ->
                // TODO validation
                onInputChanged(value.text)
            },
            hint = stringResource(id = R.string.footer_input_hint),
            keyboardActions = keyboardActions
        )
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.footer_button_shorten_it),
            onClick = onPrimaryButtonClick
        )
    }
}
