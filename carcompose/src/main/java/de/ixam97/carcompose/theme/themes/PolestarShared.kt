package de.ixam97.carcompose.theme.themes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.ixam97.carcompose.theme.CarDimensions
import de.ixam97.carcompose.theme.CarTypography
import java.io.File

// Car Ui Colors
val graphite = Color(0xFF101920)
val background = Color(0xFF141516)
val primarySurface = Color(0xFF1B1C1D)
val secondarySurface = Color(0xFF242526)
val buttonSurface = secondarySurface

val primaryDivider = Color(0xFF424242)
val secondaryDivider = Color(0xFF242526)

val mainAccentForeground = Color(0xFFFF7500)
val mainAccentBackground = Color(0xFFBF5800)

val aquamarine = Color(0xFF59F3FD)
val parisDaisy = Color(0xFFF7E948)
val sustainableBlue = Color(0xFF5D95EC)
val cinnabarRed = Color(0xFFE03C32)
val lightCold = Color(0xFFD3BD8E)
val darkGold = Color(0xFF866E4C)

val PolestarFont = if (File("/product/fonts/PolestarUnica77-Regular.otf").exists()) {
    FontFamily(
        Font(
            File("/product/fonts/PolestarUnica77-Regular.otf"),
            weight = FontWeight.Normal
        ),
        Font(
            File("/product/fonts/PolestarUnica77-Medium.otf"),
            weight = FontWeight.Medium
        )
    )
} else if (File("/product/fonts/Unica77PolestarTT-Regular.ttf").exists()) {
    FontFamily(
        Font(
            File("/product/fonts/Unica77PolestarTT-Regular.ttf")
        )
    )
} else {
    FontFamily.Default
}

val PolestarCarTypography = CarTypography(
    // defaultFontFamily = PolestarFont,
    title = TextStyle(
        fontFamily = PolestarFont,
        fontSize = 48.sp,
        fontWeight = FontWeight.Medium
    ),
    rowTitle = TextStyle(
        fontFamily = PolestarFont,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    rowContent = TextStyle(
        fontFamily = PolestarFont,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    gridTileDescrription = TextStyle(
        fontFamily = PolestarFont,
        fontSize = 23.sp,
        fontWeight = FontWeight.Normal
    ),
    defaultBody = TextStyle(
        fontFamily = PolestarFont,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
)

val PolestarSharedCarDimensions = CarDimensions(
    buttonMinWidth = 144.dp,
    buttonMinHeight = 101.dp,
    buttonRadiusPercent = 0,
)