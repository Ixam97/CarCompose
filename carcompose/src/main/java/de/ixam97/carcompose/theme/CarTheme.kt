package de.ixam97.carcompose.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

data class CarThemeConfig(
    val carTypography: CarTypography,
    val carDarkColors: CarColors,
    val carBrightColors: CarColors? = null,
    val carDimensions: CarDimensions,
    val carUiProperties: CarUiProperties,
)

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
        content = content
    )
}

@Composable
fun CarTheme(
    carTypography: CarTypography,
    carColors: CarColors,
    carDimensions: CarDimensions,
    carUiProperties: CarUiProperties,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCarTypography provides carTypography,
        LocalCarColors provides carColors,
        LocalCarDimensions provides carDimensions,
        LocalCarUiProperties provides carUiProperties
    ) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = carColors.accent,
                onPrimary = carColors.onAccentContainer,
                background = carColors.background,
                onBackground = carColors.onBackground,
                surface = carColors.primarySurface.first(),
                onSurface = carColors.onSurface
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(carColors.background)
            ) {
                content()
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
}

val GenericCarThemeConfig = CarThemeConfig(
    carTypography = GenericCarTypography,
    carDarkColors = GenericCarColors,
    carDimensions = GenericCarDimensions,
    carUiProperties = GenericCarUiProperties,
)