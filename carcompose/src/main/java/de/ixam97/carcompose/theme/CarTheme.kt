package de.ixam97.carcompose.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
    windowInsets: PaddingValues = calculateWindowInsets(),
    content: @Composable () -> Unit
) {
    CarTheme(
        carTypography = carThemeConfig.carTypography,
        carColors = if (darkTheme || carThemeConfig.carBrightColors == null) carThemeConfig.carDarkColors else carThemeConfig.carBrightColors,
        carDimensions = carThemeConfig.carDimensions,
        carUiProperties = carThemeConfig.carUiProperties,
        carShapes = carThemeConfig.carShapes,
        darkTheme = darkTheme,
        windowInsets = windowInsets,
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
    windowInsets: PaddingValues,
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
                surfaceContainerHigh = carColors.primarySurface,
                surfaceContainerHighest = carColors.secondarySurface,
                onSurface = carColors.onSurface,
                onSurfaceVariant = carColors.onSurface.copy(alpha =  0.7f),
                secondaryContainer = carColors.accentContainer.copy(alpha = 0.3f),
                onSecondaryContainer = carColors.onSurface.copy(alpha =  0.8f)
            ),
            typography = MaterialTheme.typography.copy(
                displayLarge = carTypography.title.copy(fontSize = carTypography.title.fontSize / CarTheme.SCALING_FACTOR),
                displayMedium = carTypography.title.copy(fontSize = carTypography.title.fontSize / CarTheme.SCALING_FACTOR),
                displaySmall = carTypography.title.copy(fontSize = carTypography.title.fontSize / CarTheme.SCALING_FACTOR),
                headlineLarge = carTypography.title.copy(fontSize = carTypography.title.fontSize / CarTheme.SCALING_FACTOR),
                headlineMedium = carTypography.title.copy(fontSize = carTypography.title.fontSize / CarTheme.SCALING_FACTOR),
                headlineSmall = carTypography.title.copy(fontSize = carTypography.title.fontSize / CarTheme.SCALING_FACTOR),
                titleLarge = carTypography.title.copy(fontSize = carTypography.title.fontSize / CarTheme.SCALING_FACTOR),
                titleMedium = carTypography.rowTitle.copy(fontSize = carTypography.rowTitle.fontSize / CarTheme.SCALING_FACTOR),
                titleSmall = carTypography.rowTitle.copy(fontSize = carTypography.rowTitle.fontSize / CarTheme.SCALING_FACTOR),
                bodyLarge = carTypography.rowContent.copy(fontSize = carTypography.rowContent.fontSize / CarTheme.SCALING_FACTOR),
                bodyMedium = carTypography.rowContent.copy(fontSize = carTypography.rowContent.fontSize / CarTheme.SCALING_FACTOR),
                bodySmall = carTypography.rowContent.copy(fontSize = carTypography.rowContent.fontSize / CarTheme.SCALING_FACTOR),
                labelLarge = carTypography.rowContent.copy(fontSize = carTypography.rowContent.fontSize / CarTheme.SCALING_FACTOR),
                labelMedium = carTypography.rowContent.copy(fontSize = carTypography.rowContent.fontSize / CarTheme.SCALING_FACTOR),
                labelSmall = carTypography.rowContent.copy(fontSize = carTypography.rowContent.fontSize / CarTheme.SCALING_FACTOR),
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CarTheme.carColors.background)
                    .padding(windowInsets),
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

    const val SCALING_FACTOR = 1.5f
}

val GenericCarThemeConfig = CarThemeConfig(
    carTypography = GenericCarTypography,
    carDarkColors = GenericCarColors,
    carDimensions = GenericCarDimensions,
    carUiProperties = GenericCarUiProperties,
    carShapes = GenericCarShapes
)