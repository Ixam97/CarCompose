package de.ixam97.carcompose.theme.themes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarDimensions
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.CarUiProperties
import de.ixam97.carcompose.theme.GenericCarTypography

val volvoBrightAccent = Color(0xFF4A6DA8)
val volvoBrightBackground = Color(0xFFEBEBEB)
val volvoBrightPrimarySurface = Color(0xFFF5F5F5)
val volvoBrightSecondarySurface = Color(0xFFFFFFFF)
val volvoBrightOnSurfaceActive = Color(0xFF141414)
val volvoBrightOnSurfacePassive = Color(0xFF707070)
val volvoBrightOnAccent = Color(0xFFFFFFFF)
val volvoBrightDivider = Color(0xFFE2E2E2)

val volvoDarkAccent = Color(0xFF1F78D1)
val volvoDarkBackground = Color(0xFF141414)
val volvoDarkPrimarySurface = Color(0xFF1F1F1F)
val volvoDarkSecondarySurface = Color(0xFF2E2E2E)
val volvoDarkOnSurfaceActive = Color(0xFFFFFFFF)
val volvoDarkOnSurfacePassive = Color(0xFFA2A2A2)
val volvoDarkOnAccent = Color(0xFFFFFFFF)
val volvoDarkDivider = Color(0xFF313131)

val VolvoBrightColors = CarColors(
    accent = volvoBrightAccent,
    accentContainer = listOf(volvoBrightAccent),
    background = volvoBrightBackground,
    primarySurface = listOf(volvoBrightPrimarySurface),
    secondarySurface = listOf(volvoBrightSecondarySurface),
    onBackground = volvoBrightOnSurfaceActive,
    onSurface = volvoBrightOnSurfaceActive,
    onAccentContainer = volvoBrightOnAccent,
    primaryDivider = listOf(volvoBrightDivider),
    secondaryDivider = listOf(volvoBrightDivider),
    textFieldBackground = listOf(volvoBrightSecondarySurface),
    listSectionTitleColor = volvoBrightOnSurfacePassive
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
    primaryDivider = listOf(volvoDarkDivider),
    secondaryDivider = listOf(volvoDarkDivider),
    textFieldBackground = listOf(volvoDarkSecondarySurface),
    listSectionTitleColor = volvoDarkOnSurfacePassive
)

val VolvoTypograph = GenericCarTypography.copy(
    rowTitle = TextStyle.Default.copy(fontSize = 27.5.sp),
    rowContent = TextStyle.Default.copy(fontSize = 20.sp),
)

val VolvoDimensions = CarDimensions()

val VolvoProperties = CarUiProperties(
    listSectionBackground = true,
)

val VolvoCarUxThemeConfig: CarThemeConfig = CarThemeConfig(
    carTypography = VolvoTypograph,
    carDarkColors = VolvoDarkColors,
    carBrightColors = VolvoBrightColors,
    carDimensions = VolvoDimensions,
    carUiProperties = VolvoProperties
)