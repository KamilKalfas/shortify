package com.kkalfas.shortly.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

// shortly theme
@Composable
fun ShortlyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = ShortlyTypography,
        content = content
    )
}
