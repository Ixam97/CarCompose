package de.ixam97.carcompose.components.controls

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.applyIf

data class CarSwitchColors(
    val border: Color,
    val track: Brush,
    val onTrack: Color,
    val trackChecked: Brush,
    val onTrackChecked: Color,
    val thumb: Brush,
    val onThumb: Color,
    val thumbChecked:  Brush = thumb,
    val onThumbChecked: Color = onThumb
)

data class CarSwitchDimensions(
    val trackWidth: Dp,
    val trackHeight: Dp,
    val thumbWidth: Dp,
    val thumbHeight: Dp,
    val thumbPadding: Dp
)

data class CarSwitchShapes(
    val track: Shape,
    val thumb: Shape
)

data class CarSwitchProperties(
    val uncheckedText: String,
    val checkedText: String
)

object CarSwitchDefaults {
    val colors: CarSwitchColors
        @Composable get() = CarTheme.carColors.switchColors
    val dimensions: CarSwitchDimensions
        @Composable get() = CarTheme.carDimensions.switchDimensions
    val shapes: CarSwitchShapes
        @Composable get() = CarTheme.carShapes.switchShapes
    val properties: CarSwitchProperties
        @Composable get() = CarTheme.carUiProperties.switchProperties
}

@Composable
fun CarBasicSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: ((isChecked: Boolean) -> Unit)? = null,
    enabled: Boolean = true,
    colors: CarSwitchColors = CarSwitchDefaults.colors,
    dimensions: CarSwitchDimensions = CarSwitchDefaults.dimensions,
    shapes: CarSwitchShapes = CarSwitchDefaults.shapes,
    properties: CarSwitchProperties = CarSwitchDefaults.properties,
) {
    val alignmentAnimationSpec = TweenSpec<Float>(durationMillis = 100)
    val trackColor = if (isChecked) colors.trackChecked else colors.track
    val horizontalAlignmentBias by animateFloatAsState(
        targetValue = if (isChecked) 1f else -1f,
        animationSpec = alignmentAnimationSpec
    )

    Box (
        modifier = modifier
            .height(dimensions.trackHeight)
            .width(dimensions.trackWidth)
            .clip(shapes.track)
            .alpha(if (enabled) 1f else CarTheme.carColors.disabledAlpha)
            .background(trackColor)
            .applyIf(onCheckedChange != null) {
                clickable { onCheckedChange?.invoke(!isChecked) }
            }
    ) {
        key(dimensions.thumbWidth) {
            Box(
                modifier = Modifier
                    .align(BiasAlignment(horizontalAlignmentBias, 0f))
                    .height(dimensions.thumbHeight)
                    .width(dimensions.thumbWidth)
                    .padding(dimensions.thumbPadding)
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = if (!isChecked) colors.thumb else colors.thumbChecked,
                        shape = shapes.thumb
                    )
                )
            }
        }
        if (properties.checkedText.isNotBlank() && properties.uncheckedText.isNotBlank()) {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = properties.uncheckedText,
                        style = CarTheme.carTypography.rowTitle,
                        color = if (isChecked) colors.onTrack else colors.onThumb
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = properties.checkedText,
                        style = CarTheme.carTypography.rowTitle,
                        color = if (!isChecked) colors.onTrack else colors.onThumbChecked
                    )
                }
            }
        }
    }
}

@Composable
fun CarRowSwitch(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    enabled: Boolean = true,
    state: Boolean,
    colors: CarSwitchColors = CarSwitchDefaults.colors,
    dimensions: CarSwitchDimensions = CarSwitchDefaults.dimensions,
    shapes: CarSwitchShapes = CarSwitchDefaults.shapes,
    properties: CarSwitchProperties = CarSwitchDefaults.properties,
    onStateChange: (newState: Boolean) -> Unit
) {
    val foregroundColor = CarTheme.carColors.onBackground
    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = { onStateChange(!state) }
            )
            .defaultMinSize(minHeight = 92.dp)
            .padding(
                vertical = CarTheme.carDimensions.defaultVerticalPadding,
                horizontal = CarTheme.carDimensions.defaultHorizontalPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column( modifier = Modifier.weight(1f) ) {
            Text(
                text = title,
                style = CarTheme.carTypography.rowTitle,
                color = foregroundColor.copy(
                    alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha
                )
            )
            description?.let { description ->
                Spacer(Modifier.size(CarTheme.carDimensions.rowTextSpacing))
                Text(
                    text = description,
                    style = CarTheme.carTypography.rowContent,
                    color = foregroundColor.copy(
                        alpha = if (enabled) 0.7f else (CarTheme.carColors.disabledAlpha - 0.1f)
                    )
                )
            }
        }
        Spacer(modifier = Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
        Column (
            modifier = Modifier.height(IntrinsicSize.Max),
            verticalArrangement = Arrangement.Top
        ) {
            CarBasicSwitch(
                isChecked = state,
                onCheckedChange = null, // onStateChange,
                enabled = enabled,
                colors = colors,
                dimensions = dimensions,
                shapes = shapes,
                properties = properties
            )
        }
    }
}