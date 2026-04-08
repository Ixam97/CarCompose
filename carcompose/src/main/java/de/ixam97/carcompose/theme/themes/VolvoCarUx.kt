package de.ixam97.carcompose.theme.themes

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.ixam97.carcompose.theme.CarNavIconStyle
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarDimensions
import de.ixam97.carcompose.theme.CarShapes
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.CarUiProperties
import de.ixam97.carcompose.theme.GenericCarColors
import de.ixam97.carcompose.theme.GenericCarTypography
import kotlin.collections.listOf

val volvoBrightAccent = Color(0xFF4A6DA8)
val volvoBrightBackground = Color(0xFFEBEBEB)
val volvoBrightPrimarySurface = Color(0xFFF5F5F5)
val volvoBrightSecondarySurface = Color(0xFFFFFFFF)
val volvoBrightOnSurfaceActive = Color(0xFF141414)
val volvoBrightOnSurfacePassive = Color(0xFF5C5C5C) // Color(0xFF707070)
val volvoBrightOnAccent = Color(0xFFFFFFFF)
val volvoBrightDivider = Color(0xFFE2E2E2)
val volvoBrightRadioButtonBorder = Color(0xFFC5C5C5)

val volvoDarkAccent = Color(0xFF1F78D1)
val volvoDarkBackground = Color(0xFF141414)
val volvoDarkPrimarySurface = Color(0xFF1F1F1F)
val volvoDarkSecondarySurface = Color(0xFF2E2E2E)
val volvoDarkOnSurfaceActive = Color(0xFFFFFFFF)
val volvoDarkOnSurfacePassive = Color(0xFFA2A2A2)
val volvoDarkOnAccent = Color(0xFFFFFFFF)
val volvoDarkDivider = Color(0xFF313131)
val volvoDarkRadioButtonBorder = Color(0xFF4C4C4C)

val VolvoBrightColors = CarColors(
    accent = volvoBrightAccent,
    accentContainer = listOf(volvoBrightAccent),
    background = volvoBrightBackground,
    primarySurface = listOf(volvoBrightPrimarySurface),
    secondarySurface = listOf(volvoBrightSecondarySurface),
    onBackground = volvoBrightOnSurfaceActive,
    onSurface = volvoBrightOnSurfaceActive,
    onAccentContainer = volvoBrightOnAccent,
    primaryDivider = listOf(Color.Transparent),
    secondaryDivider = listOf(volvoBrightDivider),
    textFieldBackground = listOf(volvoBrightSecondarySurface),
    listSectionTitleColor = volvoBrightOnSurfacePassive,
    disabledOverlay = Color.White.copy(alpha = GenericCarColors.disabledAlpha),
    snackBarBackground = listOf(volvoDarkSecondarySurface),
    snackBarForeground = volvoDarkOnSurfaceActive,
    snackBarAccent = volvoDarkAccent,
    segmentedButtonBackground = listOf(volvoBrightSecondarySurface),
    segmentedButtonBorder = volvoBrightBackground,
    radioButtonBorder = volvoBrightRadioButtonBorder,
    radioButtonSelectedBorder = Color.Transparent,
    radioButtonSelectedBackground = volvoBrightAccent,
    radioButtonSelector = volvoBrightOnAccent,
    backNavIconColor = volvoBrightOnSurfaceActive
)

val VolvoDarkColors = CarColors(
    accent = volvoDarkAccent,
    accentContainer = listOf(volvoDarkAccent),
    background = volvoDarkBackground,
    primarySurface = listOf(volvoDarkPrimarySurface),
    secondarySurface = listOf(volvoDarkSecondarySurface),
    onBackground = volvoDarkOnSurfaceActive,
    onSurface = volvoDarkOnSurfaceActive,
    onAccentContainer = volvoDarkOnAccent,
    primaryDivider = listOf(Color.Transparent),
    secondaryDivider = listOf(volvoDarkDivider),
    textFieldBackground = listOf(volvoDarkSecondarySurface),
    listSectionTitleColor = volvoDarkOnSurfacePassive,
    snackBarBackground = listOf(volvoBrightSecondarySurface),
    snackBarForeground = volvoBrightOnSurfaceActive,
    snackBarAccent = volvoBrightAccent,
    segmentedButtonBackground = listOf(volvoDarkSecondarySurface),
    segmentedButtonBorder = Color.Transparent,
    radioButtonBorder = volvoDarkRadioButtonBorder,
    radioButtonSelectedBorder = Color.Transparent,
    radioButtonSelectedBackground = volvoDarkAccent,
    radioButtonSelector = volvoDarkOnAccent,
    backNavIconColor = volvoDarkOnSurfaceActive
)

val VolvoTypograph = GenericCarTypography.copy(
    title = TextStyle.Default.copy(fontSize = 27.5.sp, fontWeight = FontWeight.SemiBold),
    rowTitle = TextStyle.Default.copy(fontSize = 27.5.sp),
    rowContent = TextStyle.Default.copy(fontSize = 20.sp),
)

val VolvoDimensions = CarDimensions(
    iconButtonSize = 43.dp,
    headerHeight = 106.dp,
    segmentedButtonBorderWidth = 1.75.dp,
    segmentedButtonInnerPadding = 5.dp
)

val VolvoProperties = CarUiProperties(
    listSectionBackground = true,
    backButtonIconStyle = CarNavIconStyle.ChevronBackwards,
    rowBrowseIconStyle = CarNavIconStyle.ChevronForwards
)

val VolvoShapes = CarShapes(
    buttonCornerSize = CornerSize(8.dp),
    segmentedButtonOuterCornerSize = CornerSize(50),
    textFieldShape = RoundedCornerShape(8.dp),
    radioButtonOuterShape = CircleShape
)

val VolvoCarUxThemeConfig: CarThemeConfig = CarThemeConfig(
    carTypography = VolvoTypograph,
    carDarkColors = VolvoDarkColors,
    carBrightColors = VolvoBrightColors,
    carDimensions = VolvoDimensions,
    carUiProperties = VolvoProperties,
    carShapes = VolvoShapes
)