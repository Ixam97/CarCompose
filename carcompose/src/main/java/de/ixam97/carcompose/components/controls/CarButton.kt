package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import de.ixam97.carcompose.theme.CarTheme

data class CarButtonColors(
    val backgroundBrush: Brush,
    val activeBrush: Brush,
    val textColor: Color,
    val activeTextColor: Color
)

data class CarButtonDimensions(
    val minWidth: Dp,
    val minHeight: Dp,
    val horizontalPadding: Dp,
    val verticalPadding: Dp
)

object CarButtonDefaults {
    val colors: CarButtonColors
        @Composable get () = CarButtonColors(
            backgroundBrush = CarTheme.carColors.secondarySurfaceBrush,
            activeBrush = CarTheme.carColors.accentContainerBrush,
            textColor = CarTheme.carColors.onSurface,
            activeTextColor = CarTheme.carColors.onAccentContainer
        )
    val shape: Shape
        @Composable get() = CarTheme.carShapes.buttonShape
    val dimensions: CarButtonDimensions
        @Composable get () = CarButtonDimensions(
            minWidth = CarTheme.carDimensions.buttonMinWidth,
            minHeight = CarTheme.carDimensions.buttonMinHeight,
            horizontalPadding = CarTheme.carDimensions.defaultHorizontalPadding,
            verticalPadding = CarTheme.carDimensions.defaultVerticalPadding
        )
}

@Composable
fun CarButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    active: Boolean = false,
    colors: CarButtonColors = CarButtonDefaults.colors,
    shape: Shape = CarButtonDefaults.shape,
    dimensions: CarButtonDimensions = CarButtonDefaults.dimensions,
    content: @Composable RowScope.() -> Unit
) {

    val backgroundBrush = if (active) colors.activeBrush else colors.backgroundBrush
    val foregroundColor = if (active) colors.activeTextColor else colors.textColor
    Box(
        modifier = modifier
            .heightIn(min = dimensions.minHeight)
            .widthIn(min = dimensions.minWidth)
            .clip(shape)
            .alpha(if (enabled) 1f else CarTheme.carColors.disabledAlpha)
            .background(backgroundBrush)
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        CompositionLocalProvider(
            LocalContentColor provides foregroundColor,
            LocalTextStyle provides CarTheme.carTypography.rowTitle
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = dimensions.horizontalPadding,
                        vertical = dimensions.verticalPadding
                    ),
                horizontalArrangement = Arrangement.spacedBy(CarTheme.carDimensions.defaultHorizontalPadding),
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}