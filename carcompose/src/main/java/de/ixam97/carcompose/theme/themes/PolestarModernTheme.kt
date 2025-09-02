package de.ixam97.carcompose.theme.themes

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.CarUiProperties
import de.ixam97.carcompose.theme.UiType

val PolestarModernCarColors = CarColors(
    accent = mainAccentForeground,
    accentContainer = listOf(mainAccentBackground),
    background = background,
    primarySurface = listOf(primarySurface),
    secondarySurface = listOf(secondarySurface),
    onBackground = Color.White,
    onSurface = Color.White,
    onAccentContainer = Color.White,
    primaryDivider = listOf(primaryDivider),
    secondaryDivider = listOf(secondaryDivider)
)

val PolestarModernCarDimensions = PolestarSharedCarDimensions.copy(
    // theme specific dimensions
    headerDividerHorizontalPadding = 32.dp,
    headerHeight = 134.dp,
)

val PolestarModernCarUiProperties = CarUiProperties(
    uiType = UiType.PolestarModern
)

val PolestarModernCarTypography = PolestarCarTypography.copy(
    title = PolestarCarTypography.title.copy(fontWeight = FontWeight.Normal)
)

@Composable
fun PolestarModernTheme(
    content: @Composable () -> Unit
) {
    CarTheme(
        carTypography = PolestarModernCarTypography,
        carColors = PolestarModernCarColors,
        carDimensions = PolestarModernCarDimensions,
        carUiProperties = PolestarModernCarUiProperties,
        content = content
    )
}