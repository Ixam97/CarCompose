package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme

data class CarSegmentedButtonColors(
    val background: Brush,
    val border: Color,
    val buttonContainer: Brush,
    val buttonContainerActive: Brush,
    val onButtonContainer: Color,
    val onButtonContainerActive: Color,
)

data class CarSegmentedButtonDimensions(
    val outerPadding: Dp,
    val borderWidth: Dp,
    val buttonHorizontalPadding: Dp,
    val buttonVerticalPadding: Dp,
    val buttonMinWidth: Dp,
    val buttonMinHeight: Dp
)

data class CarSegmentedButtonShapes(
    val backgroundCornerSize: CornerSize,
    val outerCornerSize: CornerSize,
    val innerCornerSize: CornerSize
)

object CarSegmentedButtonDefaults {
    val colors: CarSegmentedButtonColors
        @Composable get() = CarTheme.carColors.segmentedButtonColors
    val dimensions: CarSegmentedButtonDimensions
        @Composable get() = CarTheme.carDimensions.segmentedButtonDimensions
    val shapes: CarSegmentedButtonShapes
        @Composable get() = CarTheme.carShapes.segmentedButtonShapes
}

object CarSegmentedButton {
    data class Segment<T>(
        val content: @Composable RowScope.() -> Unit,
        val key: T,
        val onClick: (T) -> Unit = {},
        val enabled: Boolean = true,
    )
}

@Composable
fun <T> CarSegmentedButton(
    modifier: Modifier = Modifier,
    segments: List<CarSegmentedButton.Segment<T>>,
    selectedKey: T?,
    onSegmentChanged: (T?) -> Unit,
    canDeselect: Boolean = false,
    colors: CarSegmentedButtonColors = CarSegmentedButtonDefaults.colors,
    dimensions: CarSegmentedButtonDimensions = CarSegmentedButtonDefaults.dimensions,
    shapes: CarSegmentedButtonShapes = CarSegmentedButtonDefaults.shapes
) {
    if (segments.isEmpty()) {
        throw(Exception("Segmented button contents cannot be empty!"))
    }

    val outerShape = RoundedCornerShape(shapes.outerCornerSize)

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clip(outerShape)
            .border(
                width = dimensions.borderWidth,
                color = colors.border, // CarTheme.carColors.segmentedButtonBorder,
                shape = outerShape
            )
            .background(colors.background)
            .padding(dimensions.outerPadding + dimensions.borderWidth),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        segments.forEachIndexed { index, segment ->
            val enabled = segment.enabled
            CarButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {
                    if (canDeselect && selectedKey == segment.key) onSegmentChanged(null)
                    else onSegmentChanged(segment.key)
                    segment.onClick(segment.key)
                },
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        shapes.outerCornerSize,
                        shapes.innerCornerSize,
                        shapes.innerCornerSize,
                        shapes.outerCornerSize
                    )
                    (segments.size - 1) -> RoundedCornerShape(
                        shapes.innerCornerSize,
                        shapes.outerCornerSize,
                        shapes.outerCornerSize,
                        shapes.innerCornerSize
                    )
                    else -> RoundedCornerShape(shapes.innerCornerSize)
                },
                colors = CarButtonDefaults.colors.copy(
                    backgroundBrush = colors.buttonContainer,
                    activeBrush = colors.buttonContainerActive,
                    textColor = colors.onButtonContainer,
                    activeTextColor = colors.onButtonContainerActive
                ),
                dimensions = CarButtonDefaults.dimensions.copy(
                    minWidth = dimensions.buttonMinWidth,
                    minHeight = dimensions.buttonMinHeight,
                    horizontalPadding = dimensions.buttonHorizontalPadding,
                    verticalPadding = dimensions.buttonVerticalPadding
                ),
                enabled = enabled,
                active = selectedKey == segment.key,
                content = segment.content
            )
        }
    }
}