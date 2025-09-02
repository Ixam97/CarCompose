package de.ixam97.carcompose.components.controls

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.buildGradientBrush
import kotlinx.coroutines.launch

@Composable
fun CarBasicSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (isChecked: Boolean) -> Unit,
    enabled: Boolean = true,
    checkedText: String = "On",
    uncheckedText: String = "Off"
) {
    val localDensity = LocalDensity.current
    val accentContainerBrush = buildGradientBrush(CarTheme.carColors.accentContainer)
    val disabledOverlay = CarTheme.carColors.disabledOverlay

    var size by remember { mutableStateOf(IntSize.Zero) }

    val textColor = CarTheme.carColors.onSurface
    val thumbTextColor = CarTheme.carColors.onAccentContainer

    Box (
        modifier = modifier
            .height(CarTheme.carDimensions.buttonMinHeight)
            .width(CarTheme.carDimensions.buttonMinWidth * 2)
            .clip(RoundedCornerShape(CarTheme.carDimensions.buttonRadiusPercent))
            .background(CarTheme.carColors.secondarySurface.first())
            .onSizeChanged { size = it }
            .drawWithContent {
                drawContent()
                if (!enabled) drawRect(color = disabledOverlay)
            }
    ) {
        Box(ThumbElement(isChecked)) {
            with(localDensity) {
                Box(modifier = Modifier
                    .width((size.width / 2).toDp())
                    .height(size.height.toDp())
                    .background(accentContainerBrush, RoundedCornerShape(CarTheme.carDimensions.buttonRadiusPercent))
                )
            }
        }
        Row {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uncheckedText,
                    style = CarTheme.carTypography.rowTitle,
                    fontSize = 30.sp,
                    color = if (isChecked) textColor else thumbTextColor
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = checkedText,
                    style = CarTheme.carTypography.rowTitle,
                    fontSize = 30.sp,
                    color = if (!isChecked) textColor else thumbTextColor
                )
            }
        }
    }
}

private data class ThumbElement(
    val checked: Boolean
) : ModifierNodeElement<ThumbNode>() {
    override fun create(): ThumbNode {
        return ThumbNode(checked)
    }

    override fun update(node: ThumbNode) {
        if (node.checked != checked) {
            node.invalidateMeasurement()
        }
        node.checked = checked
        node.update()
    }

}

private class ThumbNode(
    var checked: Boolean
) : Modifier.Node(), LayoutModifierNode {

    override val shouldAutoInvalidate: Boolean
        get() = false

    private var offsetAnim: Animatable<Float, AnimationVector1D>? = null
    private var initialOffset: Float = Float.NaN

    override fun onAttach() {
        invalidateMeasurement()
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(Constraints())
        val offset = if (checked) placeable.width.toFloat() else 0f

        if (offsetAnim?.targetValue != offset) {
            coroutineScope.launch {
                offsetAnim?.animateTo(offset, AnimationSpec)
            }
        }

        if (initialOffset.isNaN()) {
            initialOffset = offset
        }

        return layout(placeable.width, placeable.height) {
            placeable.placeRelative(offsetAnim?.value?.toInt() ?: offset.toInt(), 0)
        }
    }

    fun update() {
        if (offsetAnim == null && !initialOffset.isNaN()) {
            offsetAnim = Animatable(initialOffset)
        }
    }

}

private val AnimationSpec = TweenSpec<Float>(durationMillis = 100)