package de.ixam97.carcompose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val genericAccent = Color(0xFF58B9CA)
val genericBackground = Color(0xFF131314)
val genericPrimarySurface = Color(0xFF252B2C)
val genericSecondarySurface = Color(0xFF00363E)
val genericOnBackground = Color(0xFFE3E3E3)
val genericOnSurface = Color(0xFFC0EAF4)
val genericOnAccent = Color(0xFF002025)


val GenericCarColors = CarColors(
    accent = genericAccent,
    accentContainer = listOf(genericAccent),
    background = genericBackground,
    primarySurface = listOf(genericPrimarySurface),
    secondarySurface = listOf(genericSecondarySurface),
    onBackground = genericOnBackground,
    onSurface = genericOnSurface,
    onAccentContainer = genericOnAccent,
    primaryDivider = listOf(genericPrimarySurface),
    secondaryDivider = listOf(genericPrimarySurface)
)

@Immutable
data class CarColors (
    val accent: Color,
    val accentContainer: List<Color>,
    val background: Color,
    val primarySurface: List<Color>,
    val secondarySurface: List<Color>,
    val onBackground: Color,
    val onSurface: Color,
    val onAccentContainer: Color,
    val primaryDivider: List<Color>,
    val secondaryDivider: List<Color>,
    val disabledAlpha: Float = 0.3f,
    val disabledOverlay: Color = Color(0f, 0f, 0f, disabledAlpha)
)

val LocalCarColors = staticCompositionLocalOf {
    GenericCarColors
}