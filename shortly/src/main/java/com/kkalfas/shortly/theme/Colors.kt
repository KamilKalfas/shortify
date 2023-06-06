package com.kkalfas.shortly.theme

import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val LightThemeColors = lightColors(
    primary = Color(0xFF2acfcf),
    secondary = Color(0xFF3B3054),
    background = Color.White,
    onBackground = Color(0xFF35323E),
    error = Color.Red
)

val Colors.backgroundSecondary: Color
    get() = Color(0xFFF0F1F6)
val Colors.lightGray : Color
    get() = Color(0xFFBFBFBF)
val Colors.gray : Color
    get() = Color(0xFF9E9AA7)
val Colors.grayishViolet : Color
    get() = Color(0xFF35323E)
val Colors.veryDarkVioletViolet : Color
    get() = Color(0xFF232127)
val Colors.divider: Color
    get() = Color(0XFFBFBFBF)
