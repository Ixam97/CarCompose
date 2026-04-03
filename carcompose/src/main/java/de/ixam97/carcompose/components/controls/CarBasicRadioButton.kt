package de.ixam97.carcompose.components.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.applyIf

data class CarRadioButtonColors(
    val borderColor: Color,
    val selectedBorderColor: Color,
    val background: Color,
    val selectedBackground: Color,
    val selectorColor: Color
)

data class CarRadioButtonDimensions(
    val outerSize: Dp,
    val innerSize: Dp,
    val borderWidth: Dp
)

data class CarRadioButtonShapes(
    val outerShape: Shape,
    val innerShape: Shape
)

object CarRadioButtonDefaults {
    val colors: CarRadioButtonColors
        @Composable get() = CarRadioButtonColors(
            borderColor = CarTheme.carColors.radioButtonBorder,
            selectedBorderColor = CarTheme.carColors.radioButtonSelectedBorder,
            background = CarTheme.carColors.radioButtonBackground,
            selectedBackground = CarTheme.carColors.radioButtonSelectedBackground,
            selectorColor = CarTheme.carColors.radioButtonSelector
        )
    val shapes: CarRadioButtonShapes
        @Composable get() = CarRadioButtonShapes(
            outerShape = CarTheme.carShapes.radioButtonOuterShape,
            innerShape = CarTheme.carShapes.radioButtonInnerShape
        )
    val dimensions: CarRadioButtonDimensions
        @Composable get() = CarRadioButtonDimensions(
            outerSize = CarTheme.carDimensions.radioButtonOuterSize,
            innerSize = CarTheme.carDimensions.radioButtonInnerSize,
            borderWidth = CarTheme.carDimensions.radioButtonBorderWidth
        )
}

@Composable
fun CarBasicRadioButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true, // TODO: Implement enabled flag
    isSelected: Boolean,
    onSelect: (() -> Unit)?,
    colors: CarRadioButtonColors = CarRadioButtonDefaults.colors,
    shapes: CarRadioButtonShapes = CarRadioButtonDefaults.shapes,
    dimensions: CarRadioButtonDimensions = CarRadioButtonDefaults.dimensions
) {
    Box(
        modifier = modifier
            .size(dimensions.outerSize)
            .clip(shapes.outerShape)
            .border(
                width = dimensions.borderWidth,
                color = if (isSelected) colors.selectedBorderColor else colors.borderColor,
                shape = shapes.outerShape
            )
            .background(if (isSelected) colors.selectedBackground else colors.background)
            .applyIf(onSelect != null) {
                clickable(onClick = onSelect!!)
            },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isSelected,
            modifier = Modifier
                .clip(shapes.innerShape),
            enter = expandIn(TweenSpec<IntSize>(durationMillis = 100)),
            exit = shrinkOut(TweenSpec<IntSize>(durationMillis = 100))
        ) {
            Box(
                modifier = Modifier
                    .size(dimensions.innerSize)
                    .background(colors.selectorColor)
            )
        }
    }
}