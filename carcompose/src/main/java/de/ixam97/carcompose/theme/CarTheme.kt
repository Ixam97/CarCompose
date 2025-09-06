package de.ixam97.carcompose.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import de.ixam97.carcompose.theme.themes.PolestarClassicTheme
import de.ixam97.carcompose.theme.themes.PolestarModernTheme

enum class UiType {
    Generic,
    PolestarClassic,
    PolestarModern
}

interface UiTheme {
    @Composable
    fun CarTheme(
        content: @Composable () -> Unit
    )
}

object CarComposeTheme {
    object Generic: UiTheme {
        @Composable
        override fun CarTheme(
            content: @Composable () -> Unit
        ) {
            GenericCarTheme(content)
        }
    }
    object PolestarClassic: UiTheme {
        @Composable
        override fun CarTheme(
            content: @Composable () -> Unit
        ) {
            PolestarClassicTheme(content)
        }
    }
    object PolestarModern: UiTheme {
        @Composable
        override fun CarTheme(
            content: @Composable () -> Unit
        ) {
            PolestarModernTheme(content)
        }
    }
}

interface CarBackstackEntry

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

@Composable
fun GenericCarTheme(
    content: @Composable () -> Unit
) {
    CarTheme(
        carTypography = GenericCarTypography,
        carColors = GenericCarColors,
        carDimensions = GenericCarDimensions,
        carUiProperties = GenericCarUiProperties,
        content = content
    )
}

@Composable
fun CarTheme(
    carUiType: UiType,
    content: @Composable () -> Unit
) {
    when (carUiType) {
        UiType.PolestarClassic -> PolestarClassicTheme(content)
        UiType.PolestarModern -> PolestarModernTheme(content)
        else -> GenericCarTheme(content)
    }
}