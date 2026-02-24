package de.ixam97.carcompose.theme.themes

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.CarUiProperties

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
    secondaryDivider = listOf(secondaryDivider),
    textFieldBackground = listOf(secondarySurface)
)

val PolestarClassicCarDimensions = PolestarSharedCarDimensions.copy(
    headerDividerHorizontalPadding = 0.dp,
    headerContentHorizontalPadding = 28.dp,
    headerHeight = 137.dp,
    iconButtonSize = 58.dp,
)

val PolestarClassicCarUiProperties = CarUiProperties(
    headerContentAlignment = Alignment.CenterStart,
    headerIconButtonDividers = false,
    headerDividerBelowTabLayout = false,
)

val PolestarClassicThemeConfig: CarThemeConfig = CarThemeConfig(
    carTypography = PolestarCarTypography,
    carDarkColors = PolestarClassicCarColors,
    carDimensions = PolestarClassicCarDimensions,
    carUiProperties = PolestarClassicCarUiProperties
)