package de.ixam97.carcompose.components.controls

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
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
    uncheckedText: String = "Off",
    interactionSource: MutableInteractionSource? = null,
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    val localDensity = LocalDensity.current
    val accentContainerBrush = buildGradientBrush(CarTheme.carColors.accentContainer)
    val disabledOverlay = CarTheme.carColors.disabledOverlay

    val textColor = CarTheme.carColors.onSurface
    val thumbTextColor = CarTheme.carColors.onAccentContainer


    val cornerPercentEdge = CarTheme.carDimensions.buttonRadiusPercent
    val cornerPercentInside = cornerPercentEdge / 10

    val thumbWidth = CarTheme.carDimensions.buttonMinWidth
    val thumbHeight = CarTheme.carDimensions.buttonMinHeight

    Box (
        modifier = modifier
            .height(thumbHeight)
            .width(thumbWidth * 2)
            .clip(RoundedCornerShape(cornerPercentEdge))
            .background(CarTheme.carColors.secondarySurface.first())
            .drawWithContent {
                drawContent()
                if (!enabled) drawRect(color = disabledOverlay)
            }
    ) {
        Box(
            modifier = Modifier
                .then(ThumbElement(interactionSource, isChecked, thumbWidth))
        ) {
            with(localDensity) {
                Box(modifier = Modifier
                    .width(thumbWidth)
                    .height(thumbHeight)
                    .background(
                        brush = accentContainerBrush,
                        shape = RoundedCornerShape(cornerPercentInside)
                    )
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
    val interactionSource: InteractionSource,
    val checked: Boolean,
    val defaultOffset: Dp
) : ModifierNodeElement<ThumbNode>() {
    override fun create() = ThumbNode(interactionSource, checked, defaultOffset)

    override fun update(node: ThumbNode) {
        node.interactionSource = interactionSource
        if (node.checked != checked) {
            node.invalidateMeasurement()
        }
        node.checked = checked
        node.update()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "switchThumb"
        properties["interactionSource"] = interactionSource
        properties["checked"] = checked
    }

}

private class ThumbNode(
    var interactionSource: InteractionSource,
    var checked: Boolean,
    val defaultOffset: Dp
) : Modifier.Node(), LayoutModifierNode {

    override val shouldAutoInvalidate: Boolean
        get() = false

    private var offsetAnim: Animatable<Float, AnimationVector1D>? = null
    private var initialOffset: Float = Float.NaN
    private var isPressed = false

    override fun onAttach() {
        coroutineScope.launch {
            var pressCount = 0
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> pressCount++
                    is PressInteraction.Release -> pressCount--
                    is PressInteraction.Cancel -> pressCount--
                }
                val pressed = pressCount > 0
                if (isPressed != pressed) {
                    isPressed = pressed
                    invalidateMeasurement()
                }
            }
        }
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(Constraints())
        val offset = if (checked) defaultOffset.toPx() else 0f

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