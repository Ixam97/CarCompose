package de.ixam97.carcompose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CarTypography (
    // val defaultFontFamily: FontFamily = FontFamily.Default,
    val title: TextStyle,
    val rowTitle: TextStyle,
    val rowContent: TextStyle,
    val gridTileDescrription: TextStyle,
    val defaultBody: TextStyle
)

val GenericCarTypography: CarTypography = CarTypography(
    title = TextStyle.Default.copy(fontSize = 40.sp, fontWeight = FontWeight.Medium),
    rowTitle = TextStyle.Default.copy(fontSize = 25.sp),
    rowContent = TextStyle.Default.copy(fontSize = 25.sp),
    gridTileDescrription = TextStyle.Default.copy(fontSize = 21.sp),
    defaultBody = TextStyle.Default.copy(fontSize = 25.sp)
)

val LocalCarTypography = staticCompositionLocalOf {
    GenericCarTypography
}