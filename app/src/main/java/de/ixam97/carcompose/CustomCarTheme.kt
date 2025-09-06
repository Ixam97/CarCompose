package de.ixam97.carcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import de.ixam97.carcompose.theme.GenericCarColors
import de.ixam97.carcompose.theme.GenericCarDimensions
import de.ixam97.carcompose.theme.GenericCarTypography
import de.ixam97.carcompose.theme.GenericCarUiProperties
import de.ixam97.carcompose.theme.UiTheme

val customCarColors = GenericCarColors.copy(
    accent = Color(0xFF447ea6),
    accentContainer = listOf(
        Color(0xFF447ea6),
        Color(0xFF77347b)
    ),
    background = Color.Black,
    secondarySurface = listOf(Color(0xFF111922)),
    onBackground = Color.White,
    onSurface = Color.White,
    onAccentContainer = Color.White,
    primaryDivider = listOf(
        Color(0xff2a1037),
        Color(0xff77347b),
        Color(0xFF447ea6),
        Color(0xff161d39),
    )
)

object CustomCarTheme : UiTheme {
    @Composable
    override fun CarTheme(content: @Composable (() -> Unit)) {
        de.ixam97.carcompose.theme.CarTheme(
            carTypography = GenericCarTypography,
            carDimensions = GenericCarDimensions,
            carUiProperties = GenericCarUiProperties,
            carColors = customCarColors,
            content = content
        )
    }

}