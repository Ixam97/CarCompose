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
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme

@Composable
fun CarSegmentedButton(
    modifier: Modifier = Modifier,
    buttonContents: List<@Composable RowScope.()-> Unit>,
    selectedIndex: Int,
    onIndexChanged: (Int) -> Unit,
    enabledIndexes: List<Boolean> = listOf(),
    canDeselect: Boolean = false
) {
    if (buttonContents.isEmpty()) {
        throw(Exception("Segmented button contents cannot be empty!"))
    }

    if (enabledIndexes.isNotEmpty() && enabledIndexes.size != buttonContents.size) {
        throw(Exception("Segmented button contents and enabled indexes do not match!"))
    }

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        buttonContents.forEachIndexed { index, buttonContent ->
            val enabled = if (enabledIndexes.isNotEmpty()) enabledIndexes[index] else true
            val cornerPercentEdge = CarTheme.carDimensions.buttonRadiusPercent
            val cornerPercentInside = cornerPercentEdge / 10
            CarButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = {
                    if (canDeselect && selectedIndex == index) onIndexChanged(-1)
                    else onIndexChanged(index)
                },
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStartPercent = cornerPercentEdge,
                        bottomStartPercent = cornerPercentEdge,
                        topEndPercent = cornerPercentInside,
                        bottomEndPercent = cornerPercentInside
                    )
                    (buttonContents.size - 1) -> RoundedCornerShape(
                        topEndPercent = cornerPercentEdge,
                        bottomEndPercent = cornerPercentEdge,
                        topStartPercent = cornerPercentInside,
                        bottomStartPercent = cornerPercentInside
                    )
                    else -> RoundedCornerShape(cornerPercentInside)
                },
                enabled = enabled,
                active = selectedIndex == index,
                content = buttonContent
            )
        }
    }
}