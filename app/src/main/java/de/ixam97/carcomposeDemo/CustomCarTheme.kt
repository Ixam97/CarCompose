package de.ixam97.carcomposeDemo

import androidx.compose.ui.graphics.Color
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.GenericCarColors
import de.ixam97.carcompose.theme.GenericCarDimensions
import de.ixam97.carcompose.theme.GenericCarTypography
import de.ixam97.carcompose.theme.GenericCarUiProperties

val customCarColors = GenericCarColors.copy(
    accent = Color(0xFF447ea6),
    accentContainer = listOf(
        Color(0xFF447ea6),
        Color(0xFF77347b)
    ),
    background = Color.Black,
    secondarySurface = listOf(Color(0xFF111922)),
    textFieldBackground = listOf(Color(0xff161d39), Color(0xFF111922), ),
    onBackground = Color.White,
    onSurface = Color.White,
    onAccentContainer = Color.White,
    primaryDivider = listOf(
        Color(0xff2a1037),
        Color(0xff77347b),
        Color(0xFF447ea6),
        Color(0xff161d39),
    )
)

val customCarDimensions = GenericCarDimensions.copy(
    buttonRadiusPercent = 25
)

val CustomCarThemeConfig = CarThemeConfig(
    carTypography = GenericCarTypography,
    carDimensions = customCarDimensions,
    carUiProperties = GenericCarUiProperties,
    carDarkColors = customCarColors
)