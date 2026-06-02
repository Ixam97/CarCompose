package de.ixam97.carcompose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import de.ixam97.carcompose.components.controls.CarRadioButtonColors
import de.ixam97.carcompose.components.controls.CarSegmentedButtonColors
import de.ixam97.carcompose.components.controls.CarSwitchColors
import de.ixam97.carcompose.utils.buildSolidBrush

val genericAccent = Color(0xFF91CEF5) // Color(0xFF58B9CA)
val genericAccentContainer = Color(0xFF91CEF5)
val genericBackground = Color(0xFF181C1F) // Color(0xFF131314)
val genericPrimarySurface = Color(0xFF282A2D) // Color(0xFF252B2C)
val genericSecondarySurface = Color(0xFF374955) // Color(0xFF00363E)
val genericOnBackground = Color(0xFFDFE3E7) // Color(0xFFE3E3E3)
val genericOnSurface = Color(0xFFD2E5F4) // Color(0xFFC0EAF4)
val genericOnAccent = Color(0xFF00344B) // Color(0xFF002025)

val genericUncheckedTrack = Color(0xFF374955)
val genericUncheckedThumb = Color(0xFFB6C9D8)


val GenericCarColors = CarColors(
    accent = genericAccent,
    accentContainer = genericAccentContainer,
    background = genericBackground,
    primarySurface = genericPrimarySurface,
    secondarySurface = genericSecondarySurface,
    onBackground = genericOnBackground,
    onSurface = genericOnSurface,
    onAccentContainer = genericOnAccent,
    primaryDivider = buildSolidBrush(Color.Transparent),
    secondaryDivider = buildSolidBrush(genericPrimarySurface),
    switchColors = CarSwitchColors(
        border = Color.Transparent,
        track = buildSolidBrush(genericUncheckedTrack),
        trackChecked = buildSolidBrush(genericAccent),
        thumb = buildSolidBrush(genericUncheckedThumb),
        thumbChecked = buildSolidBrush(genericOnAccent),
        onThumb = Color.Transparent,
        onThumbChecked = Color.Transparent,
        onTrack = Color.Transparent,
        onTrackChecked = Color.Transparent,
    )
)

@Immutable
data class CarColors (
    val accent: Color,
    val accentContainer: Color,
    val accentContainerBrush: Brush = buildSolidBrush(accentContainer),
    val background: Color,
    val primarySurface: Color,
    val primarySurfaceBrush: Brush = buildSolidBrush(primarySurface),
    val secondarySurface: Color,
    val secondarySurfaceBrush: Brush = buildSolidBrush(secondarySurface),
    val listSectionTitleColor: Color = accent,
    val listSectionBackground: Brush = primarySurfaceBrush,
    val onBackground: Color,
    val onSurface: Color,
    val onAccentContainer: Color,
    val primaryDivider: Brush,
    val secondaryDivider: Brush,
    val disabledAlpha: Float = 0.4f,
    val textFieldBackground: Brush = primarySurfaceBrush,
    val textFieldTextColor: Color = onBackground,
    val snackBarBackground: Brush = secondarySurfaceBrush,
    val snackBarForeground: Color = onSurface,
    val snackBarAccent: Color = accent,
    val backNavIconColor: Color = accent,
    val switchColors: CarSwitchColors = CarSwitchColors(
        border = Color.Transparent,
        track = secondarySurfaceBrush,
        onTrack = onSurface,
        trackChecked = secondarySurfaceBrush,
        onTrackChecked = onSurface,
        thumb = accentContainerBrush,
        onThumb = onAccentContainer
    ),
    val radioButtonColors: CarRadioButtonColors = CarRadioButtonColors(
        borderColor = primarySurface,
        selectedBorderColor = accent
    ),
    val segmentedButtonColors: CarSegmentedButtonColors = CarSegmentedButtonColors(
        background = buildSolidBrush(Color.Transparent),
        border = Color.Transparent,
        buttonContainer = secondarySurfaceBrush,
        buttonContainerActive = accentContainerBrush,
        onButtonContainer = onSurface,
        onButtonContainerActive = onAccentContainer
    )
)

val LocalCarColors = staticCompositionLocalOf {
    GenericCarColors
}