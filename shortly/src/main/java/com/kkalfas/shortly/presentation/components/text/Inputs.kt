package com.kkalfas.shortly.presentation.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kkalfas.shortly.R
import com.kkalfas.shortly.presentation.theme.grayishViolet
import com.kkalfas.shortly.presentation.theme.lightGray

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    hint: String,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val inputUnFocusedColors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Color.Transparent,
        errorBorderColor = MaterialTheme.colors.error,
        backgroundColor = MaterialTheme.colors.background,
        textColor = MaterialTheme.colors.grayishViolet,
        placeholderColor = MaterialTheme.colors.lightGray
    )
    val inputFocusedColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Transparent,
        errorBorderColor = MaterialTheme.colors.error,
        backgroundColor = MaterialTheme.colors.background,
        errorLabelColor = MaterialTheme.colors.error,
        textColor = MaterialTheme.colors.grayishViolet,
        placeholderColor = MaterialTheme.colors.lightGray
    )

    var colors by remember { mutableStateOf(inputUnFocusedColors) }
    var textFieldValue by remember { mutableStateOf(value) }
    val (placeholderText, placeholderTextColor) = if (isError) stringResource(id = R.string.footer_input_error) to MaterialTheme.colors.error
    else hint to MaterialTheme.colors.lightGray
    TextFieldWithoutPadding(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                colors = if (it.hasFocus) inputFocusedColors
                else inputUnFocusedColors
            },
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onValueChange(it)
        },
        placeholder = {
            Placeholder(
                modifier = Modifier.fillMaxWidth(),
                text = placeholderText,
                textColor = placeholderTextColor
            )
        },
        isError = isError,
        singleLine = true,
        colors = colors,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = RectangleShape
    )
}

// TextFieldDefaults.MinHeight is 56.dp
private val TextFieldHeight = 48.dp

/**
 * copy of androidx.compose.material.TextField
 * to remove padding
 */
@Composable
private fun TextFieldWithoutPadding(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.TextFieldShape,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors()
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    @OptIn(ExperimentalMaterialApi::class)
    (BasicTextField(
        value = value,
        modifier = modifier
            .background(colors.backgroundColor(enabled).value, shape)
            .width(48.dp)
            .indicatorLine(enabled, isError, interactionSource, colors)
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldHeight
            ).border(1.dp, if (isError) MaterialTheme.colors.error else Color.Transparent),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            // places leading icon, text field with label and placeholder, trailing icon
            TextFieldDefaults.TextFieldDecorationBox(
                value = value.text,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 1.dp)
            )
        }
    ))
}
