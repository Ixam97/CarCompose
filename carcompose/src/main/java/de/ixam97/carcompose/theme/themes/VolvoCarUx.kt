package de.ixam97.carcompose.theme.themes

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.ixam97.carcompose.components.controls.CarRadioButtonColors
import de.ixam97.carcompose.components.controls.CarRadioButtonShapes
import de.ixam97.carcompose.components.controls.CarSegmentedButtonColors
import de.ixam97.carcompose.components.controls.CarSegmentedButtonDimensions
import de.ixam97.carcompose.components.controls.CarSegmentedButtonShapes
import de.ixam97.carcompose.components.controls.CarSwitchColors
import de.ixam97.carcompose.components.controls.CarSwitchDimensions
import de.ixam97.carcompose.components.controls.CarSwitchProperties
import de.ixam97.carcompose.components.controls.CarSwitchShapes
import de.ixam97.carcompose.theme.CarNavIconStyle
import de.ixam97.carcompose.theme.CarColors
import de.ixam97.carcompose.theme.CarDimensions
import de.ixam97.carcompose.theme.CarShapes
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.CarUiProperties
import de.ixam97.carcompose.theme.GenericCarTypography
import de.ixam97.carcompose.utils.buildSolidBrush

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
    accentContainer = volvoBrightAccent,
    background = volvoBrightBackground,
    primarySurface = volvoBrightPrimarySurface,
    secondarySurface = volvoBrightSecondarySurface,
    onBackground = volvoBrightOnSurfaceActive,
    onSurface = volvoBrightOnSurfaceActive,
    onAccentContainer = volvoBrightOnAccent,
    primaryDivider = buildSolidBrush(Color.Transparent),
    secondaryDivider = buildSolidBrush(volvoBrightDivider),
    textFieldBackground = buildSolidBrush(volvoBrightSecondarySurface),
    listSectionTitleColor = volvoBrightOnSurfacePassive,
    // disabledOverlay = Color.White.copy(alpha = GenericCarColors.disabledAlpha),
    snackBarBackground = buildSolidBrush(volvoDarkSecondarySurface),
    snackBarForeground = volvoDarkOnSurfaceActive,
    snackBarAccent = volvoDarkAccent,
    backNavIconColor = volvoBrightOnSurfaceActive,
    switchColors = CarSwitchColors(
        border = Color.Transparent,
        track = buildSolidBrush(volvoBrightRadioButtonBorder),
        onTrack = Color.Transparent,
        trackChecked = buildSolidBrush(volvoBrightAccent),
        onTrackChecked = Color.Transparent,
        thumb = buildSolidBrush(volvoBrightOnAccent),
        onThumb = Color.Transparent
    ),
    radioButtonColors = CarRadioButtonColors(
        borderColor = volvoBrightRadioButtonBorder,
        selectedBorderColor = Color.Transparent,
        selectedBackground = volvoBrightAccent,
        selectorColor = volvoBrightOnAccent,
    ),
    segmentedButtonColors = CarSegmentedButtonColors(
        background = buildSolidBrush(volvoBrightSecondarySurface),
        border = volvoBrightBackground,
        buttonContainer = buildSolidBrush(volvoBrightSecondarySurface),
        buttonContainerActive = buildSolidBrush(volvoBrightAccent),
        onButtonContainer = volvoBrightOnSurfaceActive,
        onButtonContainerActive = volvoBrightOnAccent
    )
)

val VolvoDarkColors = CarColors(
    accent = volvoDarkAccent,
    accentContainer = volvoDarkAccent,
    background = volvoDarkBackground,
    primarySurface = volvoDarkPrimarySurface,
    secondarySurface = volvoDarkSecondarySurface,
    onBackground = volvoDarkOnSurfaceActive,
    onSurface = volvoDarkOnSurfaceActive,
    onAccentContainer = volvoDarkOnAccent,
    primaryDivider = buildSolidBrush(Color.Transparent),
    secondaryDivider = buildSolidBrush(volvoDarkDivider),
    textFieldBackground = buildSolidBrush(volvoDarkSecondarySurface),
    listSectionTitleColor = volvoDarkOnSurfacePassive,
    snackBarBackground = buildSolidBrush(volvoBrightSecondarySurface),
    snackBarForeground = volvoBrightOnSurfaceActive,
    snackBarAccent = volvoBrightAccent,
    backNavIconColor = volvoDarkOnSurfaceActive,
    switchColors = CarSwitchColors(
        border = Color.Transparent,
        track = buildSolidBrush(volvoDarkRadioButtonBorder),
        onTrack = Color.Transparent,
        trackChecked = buildSolidBrush(volvoDarkAccent),
        onTrackChecked = Color.Transparent,
        thumb = buildSolidBrush(volvoDarkOnAccent),
        onThumb = Color.Transparent
    ),
    radioButtonColors = CarRadioButtonColors(
        borderColor = volvoDarkRadioButtonBorder,
        selectedBorderColor = Color.Transparent,
        selectedBackground = volvoDarkAccent,
        selectorColor = volvoDarkOnAccent,
    ),
    segmentedButtonColors = CarSegmentedButtonColors(
        background = buildSolidBrush(volvoDarkSecondarySurface),
        border = Color.Transparent,
        buttonContainer = buildSolidBrush(volvoDarkSecondarySurface),
        buttonContainerActive = buildSolidBrush(volvoDarkAccent),
        onButtonContainer = volvoDarkOnSurfaceActive,
        onButtonContainerActive = volvoDarkOnAccent
    )
)

val VolvoTypograph = GenericCarTypography.copy(
    title = TextStyle.Default.copy(fontSize = 27.5.sp, fontWeight = FontWeight.SemiBold),
    rowTitle = TextStyle.Default.copy(fontSize = 27.5.sp),
    rowContent = TextStyle.Default.copy(fontSize = 20.sp),
)

val VolvoDimensions = CarDimensions(
    iconButtonSize = 43.dp,
    headerHeight = 106.dp,
    switchDimensions = CarSwitchDimensions(
        trackWidth = 78.dp,
        trackHeight = 43.dp,
        thumbWidth = 43.dp,
        thumbHeight = 43.dp,
        thumbPadding = 3.4.dp
    ),
    segmentedButtonDimensions = CarSegmentedButtonDimensions(
        padding = 5.dp,
        borderWidth = 1.75.dp
    )
)

val VolvoProperties = CarUiProperties(
    listSectionBackground = true,
    backButtonIconStyle = CarNavIconStyle.ChevronBackwards,
    rowBrowseIconStyle = CarNavIconStyle.ChevronForwards,
    switchProperties = CarSwitchProperties(
        uncheckedText = "",
        checkedText = ""
    )
)

val VolvoShapes = CarShapes(
    defaultOuterCornerSize = CornerSize(8.dp),
    textFieldShape = RoundedCornerShape(8.dp),
    switchShapes = CarSwitchShapes(
        track = RoundedCornerShape(50),
        thumb = RoundedCornerShape(50)
    ),
    radioButtonShapes = CarRadioButtonShapes(
        outerShape = RoundedCornerShape(50)
    ),
    segmentedButtonShapes = CarSegmentedButtonShapes(
        backgroundCornerSize = CornerSize(50),
        outerCornerSize = CornerSize(50),
        innerCornerSize = CornerSize(50)
    )
)

val VolvoCarUxThemeConfig: CarThemeConfig = CarThemeConfig(
    carTypography = VolvoTypograph,
    carDarkColors = VolvoDarkColors,
    carBrightColors = VolvoBrightColors,
    carDimensions = VolvoDimensions,
    carUiProperties = VolvoProperties,
    carShapes = VolvoShapes
)