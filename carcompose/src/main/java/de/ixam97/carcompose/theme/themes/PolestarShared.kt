package de.ixam97.carcompose.theme.themes

import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.ixam97.carcompose.components.controls.CarRadioButtonColors
import de.ixam97.carcompose.components.controls.CarRadioButtonDimensions
import de.ixam97.carcompose.components.controls.CarRadioButtonShapes
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarDimensions
import de.ixam97.carcompose.theme.CarShapes
import de.ixam97.carcompose.theme.CarTypography
import de.ixam97.carcompose.utils.buildSolidBrush
import java.io.File

// Car Ui Colors
val polestarGraphite = Color(0xFF101920)
val polestarBackground = Color(0xFF141516)
val polestarPrimarySurface = Color(0xFF1B1C1D)
val polestarSecondarySurface = Color(0xFF242526)
val polestarButtonSurface = polestarSecondarySurface

val polestarRadioButtonBorder = Color(0xFF757575)
val polestarPrimaryDivider = Color(0xFF424242)
val polestarSecondaryDivider = Color(0xFF242526)

val polestarMainAccentForeground = Color(0xFFFF7500)
val polestarMainAccentBackground = Color(0xFFBF5800)

val polestarAquamarine = Color(0xFF59F3FD)
val polestarParisDaisy = Color(0xFFF7E948)
val polestarSustainableBlue = Color(0xFF5D95EC)
val polestarCinnabarRed = Color(0xFFE03C32)
val polestarLightCold = Color(0xFFD3BD8E)
val polestarDarkGold = Color(0xFF866E4C)

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
    gridTileDescription = TextStyle(
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
    radioButtonDimensions = CarRadioButtonDimensions(
        outerSize = 60.dp,
        innerSize = 37.dp,
        borderWidth = 2.dp
    )
)

val PolestarSharedColors = CarColors(
    accent = polestarMainAccentForeground,
    accentContainer = polestarMainAccentBackground,
    background = Color.Black,
    primarySurface = polestarPrimarySurface,
    secondarySurface = polestarSecondarySurface,
    onBackground = Color.White,
    onSurface = Color.White,
    onAccentContainer = Color.White,
    primaryDivider = buildSolidBrush(polestarMainAccentForeground),
    secondaryDivider = buildSolidBrush(polestarSecondaryDivider),
    textFieldBackground = buildSolidBrush(polestarSecondarySurface),
    snackBarAccent = polestarParisDaisy,
    snackBarForeground = Color.White,
    snackBarBackground = buildSolidBrush(polestarGraphite),
    radioButtonColors = CarRadioButtonColors(
        borderColor = polestarRadioButtonBorder,
        selectorColor = polestarMainAccentBackground,
        selectedBorderColor = polestarMainAccentBackground
    ),
)

val PolestarSharedShapes = CarShapes(
    defaultOuterCornerSize = ZeroCornerSize,
    radioButtonShapes = CarRadioButtonShapes(
        outerShape = RectangleShape
    )
)