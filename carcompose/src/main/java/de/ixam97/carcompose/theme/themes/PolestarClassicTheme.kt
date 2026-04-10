package de.ixam97.carcompose.theme.themes

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarNavIconStyle
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.CarUiProperties
import de.ixam97.carcompose.utils.buildSolidBrush

val PolestarClassicCarColors = PolestarSharedColors.copy(
    background = Color.Black,
    primaryDivider = buildSolidBrush(polestarMainAccentForeground)
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
    rowBrowseIconStyle = CarNavIconStyle.ChevronForwardsSmall
)

val PolestarClassicThemeConfig: CarThemeConfig = CarThemeConfig(
    carTypography = PolestarCarTypography,
    carDarkColors = PolestarClassicCarColors,
    carDimensions = PolestarClassicCarDimensions,
    carUiProperties = PolestarClassicCarUiProperties,
    carShapes = PolestarSharedShapes
)