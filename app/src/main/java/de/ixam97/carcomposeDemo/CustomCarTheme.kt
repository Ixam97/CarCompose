package de.ixam97.carcomposeDemo

import androidx.compose.ui.graphics.Color
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.GenericCarColors
import de.ixam97.carcompose.theme.GenericCarDimensions
import de.ixam97.carcompose.theme.GenericCarShapes
import de.ixam97.carcompose.theme.GenericCarTypography
import de.ixam97.carcompose.theme.GenericCarUiProperties

val customCarColors = CarColors(
    accent = Color(0xFF447ea6),
    accentContainer = listOf(
        Color(0xFF447ea6),
        Color(0xFF77347b)
    ),
    background = Color.Black,
    secondarySurface = listOf(Color(0xff161d39), Color(0xFF111922)),
    textFieldBackground = listOf(Color(0xff161d39), Color(0xFF111922),),
    onBackground = Color.White,
    onSurface = Color.White,
    onAccentContainer = Color.White,
    primaryDivider = listOf(
        Color(0xff2a1037),
        Color(0xff77347b),
        Color(0xFF447ea6),
        Color(0xff161d39),
    ),
    primarySurface = GenericCarColors.primarySurface,
    secondaryDivider = GenericCarColors.secondaryDivider,
    radioButtonBorder = GenericCarColors.radioButtonBorder
)

val customCarDimensions = GenericCarDimensions

val CustomCarThemeConfig = CarThemeConfig(
    carTypography = GenericCarTypography,
    carDimensions = customCarDimensions,
    carUiProperties = GenericCarUiProperties,
    carDarkColors = customCarColors,
    carShapes = GenericCarShapes
)