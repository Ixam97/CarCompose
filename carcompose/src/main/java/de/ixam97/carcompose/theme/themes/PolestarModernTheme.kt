package de.ixam97.carcompose.theme.themes

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.CarUiProperties
import de.ixam97.carcompose.utils.buildSolidBrush

val PolestarModernCarColors = PolestarSharedColors.copy(
    background = polestarBackground,
    primaryDivider = buildSolidBrush(polestarPrimaryDivider),
)

val PolestarModernCarDimensions = PolestarSharedCarDimensions.copy(
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