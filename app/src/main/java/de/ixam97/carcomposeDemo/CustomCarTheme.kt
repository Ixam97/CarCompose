package de.ixam97.carcomposeDemo

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarDimensions
import de.ixam97.carcompose.theme.CarShapes
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.GenericCarColors
import de.ixam97.carcompose.theme.GenericCarTypography
import de.ixam97.carcompose.theme.GenericCarUiProperties
import de.ixam97.carcompose.utils.buildGradientBrush

val customCarColors = CarColors(
    accent = Color(0xFF447ea6),
    accentContainer = Color(0xFF447ea6),
    accentContainerBrush = buildGradientBrush(listOf(
        Color(0xFF447ea6),
        Color(0xFF77347b)
    )),
    background = Color.Black,
    secondarySurface = Color(0xff161d39),
    secondarySurfaceBrush = buildGradientBrush(listOf(
        Color(0xff161d39),
        Color(0xFF111922)
    )),
    textFieldBackground = buildGradientBrush(listOf(
        Color(0xff161d39),
        Color(0xFF111922)
    )),
    onBackground = Color.White,
    onSurface = Color.White,
    onAccentContainer = Color.White,
    primaryDivider = buildGradientBrush(listOf(
        Color(0xff2a1037),
        Color(0xff77347b),
        Color(0xFF447ea6),
        Color(0xff161d39)
    )),
    primarySurface = GenericCarColors.primarySurface,
    primarySurfaceBrush = GenericCarColors.primarySurfaceBrush,
    secondaryDivider = GenericCarColors.secondaryDivider,
)

val customCarDimensions = CarDimensions(
    buttonMinHeight = 60.dp
)

val customCarShapes = CarShapes(
    defaultOuterCornerSize = CornerSize(25),
    defaultInnerCornerSize = CornerSize(5.dp)
)

val CustomCarThemeConfig = CarThemeConfig(
    carTypography = GenericCarTypography,
    carDimensions = customCarDimensions,
    carUiProperties = GenericCarUiProperties,
    carDarkColors = customCarColors,
    carShapes = customCarShapes
)