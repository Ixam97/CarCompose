package de.ixam97.carcompose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class CarDimensions (
    val defaultHorizontalPadding: Dp = 32.dp,
    val defaultVerticalPadding: Dp = 14.dp,
    val mediumVerticalPadding: Dp = 24.dp,
    val largeVerticalPadding: Dp = 28.dp,
    val rowTextSpacing: Dp = 10.dp,
    val rowMinHeight: Dp = 80.dp,
    val headerDividerHorizontalPadding: Dp = 0.dp,
    val headerContentHorizontalPadding: Dp = 32.dp,
    val headerHeight: Dp = 90.dp,
    val buttonRadiusPercent: Int = 50,
    val buttonMinHeight: Dp = 80.dp,
    val buttonMinWidth: Dp = 100.dp,
    val iconButtonSize: Dp = 48.dp,
    val columnDefaultMaxWidth: Dp = 1165.dp
)

val GenericCarDimensions = CarDimensions()

val LocalCarDimensions = staticCompositionLocalOf {
    GenericCarDimensions
}