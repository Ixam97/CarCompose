package de.ixam97.carcompose.theme.themes

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.CarUiProperties

val PolestarModernCarColors = PolestarSharedColors.copy(
    background = background,
    primaryDivider = listOf(primaryDivider),
)

val PolestarModernCarDimensions = PolestarSharedCarDimensions.copy(
    // theme specific dimensions
    headerDividerHorizontalPadding = 32.dp,
    headerHeight = 134.dp,
)

val PolestarModernCarUiProperties = CarUiProperties()

val PolestarModernCarTypography = PolestarCarTypography.copy(
    title = PolestarCarTypography.title.copy(fontWeight = FontWeight.Normal)
)

val PolestarModernThemeConfig = CarThemeConfig(
    carTypography = PolestarModernCarTypography,
    carDarkColors = PolestarModernCarColors,
    carDimensions = PolestarModernCarDimensions,
    carUiProperties = PolestarModernCarUiProperties,
    carShapes = PolestarSharedShapes
)