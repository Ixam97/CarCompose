package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme

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
    shape: Shape = CarButtonDefaults.shape,
    dimensions: CarButtonDimensions = CarButtonDefaults.dimensions
) {
    if (segments.isEmpty()) {
        throw(Exception("Segmented button contents cannot be empty!"))
    }

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        segments.forEachIndexed { index, segment ->
            val enabled = segment.enabled
            val cornerPercentEdge = CarTheme.carDimensions.buttonRadiusPercent
            val cornerPercentInside = cornerPercentEdge / 10
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
                        topStartPercent = cornerPercentEdge,
                        bottomStartPercent = cornerPercentEdge,
                        topEndPercent = cornerPercentInside,
                        bottomEndPercent = cornerPercentInside
                    )
                    (segments.size - 1) -> RoundedCornerShape(
                        topEndPercent = cornerPercentEdge,
                        bottomEndPercent = cornerPercentEdge,
                        topStartPercent = cornerPercentInside,
                        bottomStartPercent = cornerPercentInside
                    )
                    else -> RoundedCornerShape(cornerPercentInside)
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