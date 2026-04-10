package de.ixam97.carcompose.components.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.R
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.applyIf

data class CarRadioButtonColors(
    val borderColor: Color,
    val selectedBorderColor: Color,
    val background: Color = Color.Transparent,
    val selectedBackground: Color = background,
    val selectorColor: Color = selectedBorderColor
)

data class CarRadioButtonDimensions(
    val outerSize: Dp,
    val innerSize: Dp = outerSize / 2.0f,
    val borderWidth: Dp
)

data class CarRadioButtonShapes(
    val outerShape: Shape,
    val innerShape: Shape = outerShape
)

object CarRadioButtonDefaults {
    val colors: CarRadioButtonColors
        @Composable get() = CarTheme.carColors.radioButtonColors
    val shapes: CarRadioButtonShapes
        @Composable get() = CarTheme.carShapes.radioButtonShapes
    val dimensions: CarRadioButtonDimensions
        @Composable get() = CarTheme.carDimensions.radioButtonDimensions
}

object CarCheckboxDefaults {
    val colors: CarRadioButtonColors
        @Composable get() = CarTheme.carColors.radioButtonColors
    val shapes: CarRadioButtonShapes
        @Composable get() = CarTheme.carShapes.checkboxShapes
    val dimensions: CarRadioButtonDimensions
        @Composable get() = CarTheme.carDimensions.checkboxDimensions
}

@Composable
fun CarBasicRadioButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isSelected: Boolean,
    onSelect: (() -> Unit)?,
    colors: CarRadioButtonColors = CarRadioButtonDefaults.colors,
    shapes: CarRadioButtonShapes = CarRadioButtonDefaults.shapes,
    dimensions: CarRadioButtonDimensions = CarRadioButtonDefaults.dimensions,
    boxContent: @Composable (contentSize: Dp) -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.selectorColor)
        )
    }
) {
    Box(
        modifier = modifier
            .size(dimensions.outerSize)
            .clip(shapes.outerShape)
            .alpha(if (enabled) 1f else CarTheme.carColors.disabledAlpha)
            .border(
                width = dimensions.borderWidth,
                color = if (isSelected) colors.selectedBorderColor else colors.borderColor,
                shape = shapes.outerShape
            )
            .background(if (isSelected) colors.selectedBackground else colors.background)
            .applyIf(onSelect != null && enabled) {
                clickable(onClick = onSelect!!)
            },
        contentAlignment = Alignment.Center
    ) {
        // Using padding instead of size to archive a more consistent look on low dip displays.
        val calculatedPadding = ((dimensions.outerSize - dimensions.innerSize) / 2.0f)
        val animationDurationMillis = 100

        val contentSize by animateDpAsState(
            targetValue = if (isSelected) dimensions.innerSize else 0.dp,
            animationSpec = TweenSpec(animationDurationMillis)
        )

        Box(
            modifier = Modifier
                .padding(calculatedPadding)
                .clip(shapes.innerShape)
        ) {
            AnimatedVisibility(
                visible = isSelected,
                enter = expandIn(TweenSpec(animationDurationMillis)),
                exit = shrinkOut(TweenSpec(animationDurationMillis))
            ) {
                boxContent(contentSize)
            }
        }
    }
}

@Composable
fun CarRowRadioButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String? = null,
    description: String? = null,
    isSelected: Boolean,
    onSelect: () -> Unit,
    colors: CarRadioButtonColors = CarRadioButtonDefaults.colors,
    shapes: CarRadioButtonShapes = CarRadioButtonDefaults.shapes,
    dimensions: CarRadioButtonDimensions = CarRadioButtonDefaults.dimensions,
    leadingContent: @Composable (() -> Unit)? = null,
    descriptionContent: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    CarRow(
        modifier = modifier,
        enabled = enabled,
        browsable = true,
        browsableType = CarRowBrowsableType.Hidden,
        onBrowse = onSelect,
        title = title,
        description = description,
        leadingContent = leadingContent,
        descriptionContent = descriptionContent,
        content = content,
        trailingContent = {
            CarBasicRadioButton(
                isSelected = isSelected,
                enabled = enabled,
                onSelect = null,
                colors = colors,
                shapes = shapes,
                dimensions = dimensions
            )
        }
    )
}

@Composable
fun CarRowCheckbox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String? = null,
    description: String? = null,
    isSelected: Boolean,
    onSelect: () -> Unit,
    colors: CarRadioButtonColors = CarCheckboxDefaults.colors,
    shapes: CarRadioButtonShapes = CarCheckboxDefaults.shapes,
    dimensions: CarRadioButtonDimensions = CarCheckboxDefaults.dimensions,
    leadingContent: @Composable (() -> Unit)? = null,
    descriptionContent: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    CarRow(
        modifier = modifier,
        enabled = enabled,
        browsable = true,
        browsableType = CarRowBrowsableType.Hidden,
        onBrowse = onSelect,
        title = title,
        description = description,
        leadingContent = leadingContent,
        descriptionContent = descriptionContent,
        content = content,
        trailingContent = {
            CarBasicRadioButton(
                isSelected = isSelected,
                enabled = enabled,
                onSelect = null,
                colors = colors,
                shapes = shapes,
                dimensions = dimensions.copy(innerSize = dimensions.outerSize)
            ) { contentSize ->
                Icon(
                    modifier = Modifier.size(contentSize),
                    painter = painterResource(R.drawable.ic_checkmark_48),
                    contentDescription = null,
                    tint = colors.selectorColor
                )
            }
        }
    )
}