package de.ixam97.carcompose.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import de.ixam97.carcompose.components.layout.CarSnackBarHost
import de.ixam97.carcompose.components.layout.CarSnackBarHostState
import de.ixam97.carcompose.components.layout.LocalCarSnackBarState
import de.ixam97.carcompose.theme.themes.PolestarClassicThemeConfig
import de.ixam97.carcompose.theme.themes.PolestarModernThemeConfig
import de.ixam97.carcompose.theme.themes.VolvoCarUxThemeConfig
import de.ixam97.carcompose.utils.calculateWindowInsets

data class CarThemeConfig(
    val carTypography: CarTypography,
    val carDarkColors: CarColors,
    val carBrightColors: CarColors? = null,
    val carDimensions: CarDimensions,
    val carUiProperties: CarUiProperties,
    val carShapes: CarShapes
)

fun autodetectCarThemeConfig(fallback: CarThemeConfig = GenericCarThemeConfig) : CarThemeConfig {
    return when (Build.BRAND) {
        "Polestar" -> when (Build.DEVICE) {
            "ihu_abl_car", "ihu42", "ihu_emulator" -> PolestarClassicThemeConfig
            else -> PolestarModernThemeConfig
        }
        "VolvoCars" -> VolvoCarUxThemeConfig
        else -> fallback
    }
}

@Composable
fun CarTheme(
    carThemeConfig: CarThemeConfig,
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    CarTheme(
        carTypography = carThemeConfig.carTypography,
        carColors = if (darkTheme || carThemeConfig.carBrightColors == null) carThemeConfig.carDarkColors else carThemeConfig.carBrightColors,
        carDimensions = carThemeConfig.carDimensions,
        carUiProperties = carThemeConfig.carUiProperties,
        carShapes = carThemeConfig.carShapes,
        darkTheme = darkTheme,
        content = content
    )
}

@Composable
fun CarTheme(
    carTypography: CarTypography,
    carColors: CarColors,
    carDimensions: CarDimensions,
    carUiProperties: CarUiProperties,
    carShapes: CarShapes,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {

    val carSnackBarHostState = remember { CarSnackBarHostState() }

    CompositionLocalProvider(
        LocalCarTypography provides carTypography,
        LocalCarColors provides carColors,
        LocalCarDimensions provides carDimensions,
        LocalCarUiProperties provides carUiProperties,
        LocalCarShapes provides carShapes,
        LocalCarSnackBarState provides carSnackBarHostState,
    ) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = carColors.accent,
                onPrimary = carColors.onAccentContainer,
                background = carColors.background,
                onBackground = carColors.onBackground,
                surface = carColors.primarySurface,
                onSurface = carColors.onSurface
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CarTheme.carColors.background)
                    .padding(calculateWindowInsets()),
            ) {
                val view = LocalView.current
                if (!view.isInEditMode) {
                    SideEffect {
                        val window = (view.context as Activity).window
                        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
                    }
                }
                content()

                CarSnackBarHost(
                    carSnackBarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .widthIn(max = CarTheme.carDimensions.columnDefaultMaxWidth)
                )
            }
        }
    }
}

object CarTheme {
    val carTypography: CarTypography
        @Composable
        get() = LocalCarTypography.current
    val carColors: CarColors
        @Composable
        get() = LocalCarColors.current
    val carDimensions: CarDimensions
        @Composable
        get() = LocalCarDimensions.current
    val carUiProperties: CarUiProperties
        @Composable
        get() = LocalCarUiProperties.current
    val carShapes: CarShapes
        @Composable
        get() = LocalCarShapes.current
}

val GenericCarThemeConfig = CarThemeConfig(
    carTypography = GenericCarTypography,
    carDarkColors = GenericCarColors,
    carDimensions = GenericCarDimensions,
    carUiProperties = GenericCarUiProperties,
    carShapes = GenericCarShapes
)