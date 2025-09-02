package de.ixam97.carcompose.theme.themes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.CarUiProperties
import de.ixam97.carcompose.theme.UiType

val PolestarClassicCarColors = CarColors(
    accent = mainAccentForeground,
    accentContainer = listOf(mainAccentBackground),
    background = Color.Black,
    primarySurface = listOf(primarySurface),
    secondarySurface = listOf(secondarySurface),
    onBackground = Color.White,
    onSurface = Color.White,
    onAccentContainer = Color.White,
    primaryDivider = listOf(mainAccentForeground),
    secondaryDivider = listOf(secondaryDivider)
)

val PolestarClassicCarDimensions = PolestarSharedCarDimensions.copy(
    headerDividerHorizontalPadding = 0.dp,
    headerContentHorizontalPadding = 28.dp,
    headerHeight = 137.dp,
    iconButtonSize = 58.dp,
)

val PolestarClassicCarUiProperties = CarUiProperties(
    uiType = UiType.PolestarClassic,
    headerContentAlignment = Alignment.CenterStart,
    headerIconButtonDividers = false,
    headerDividerBelowTabLayout = false,
)

@Composable
fun PolestarClassicTheme(
    content: @Composable () -> Unit
) {
    CarTheme(
        carTypography = PolestarCarTypography,
        carColors = PolestarClassicCarColors,
        carDimensions = PolestarClassicCarDimensions,
        carUiProperties = PolestarClassicCarUiProperties,
        content = content
    )
}