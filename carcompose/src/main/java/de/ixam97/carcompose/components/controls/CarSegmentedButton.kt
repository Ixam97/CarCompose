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
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.buildGradientBrush

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
    colors: CarButtonColors = CarButtonDefaults.colors,
    outerCornerSize: CornerSize = CarTheme.carShapes.segmentedButtonOuterCornerSize,
    innerCornerSize: CornerSize = CarTheme.carShapes.segmentedButtonInnerCornerSize,
    dimensions: CarButtonDimensions = CarButtonDefaults.dimensions
) {
    if (segments.isEmpty()) {
        throw(Exception("Segmented button contents cannot be empty!"))
    }

    val outerShape = RoundedCornerShape(outerCornerSize)

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clip(outerShape)
            .border(
                width = CarTheme.carDimensions.segmentedButtonBorderWidth,
                color = CarTheme.carColors.segmentedButtonBorder,
                shape = outerShape
            )
            .background(buildGradientBrush(CarTheme.carColors.segmentedButtonBackground))
            .padding(CarTheme.carDimensions.segmentedButtonInnerPadding + CarTheme.carDimensions.segmentedButtonBorderWidth),
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
                        outerCornerSize,
                        innerCornerSize,
                        innerCornerSize,
                        outerCornerSize
                    )
                    (segments.size - 1) -> RoundedCornerShape(
                        innerCornerSize,
                        outerCornerSize,
                        outerCornerSize,
                        innerCornerSize
                    )
                    else -> RoundedCornerShape(innerCornerSize)
                },
                colors = colors,
                dimensions = dimensions,
                enabled = enabled,
                active = selectedKey == segment.key,
                content = segment.content
            )
        }
    }
}