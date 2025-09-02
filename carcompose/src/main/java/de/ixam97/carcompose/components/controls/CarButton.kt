package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.buildGradientBrush

data class CarButtonColors(
    val backgroundBrush: Brush,
    val activeBrush: Brush,
    val textColor: Color,
    val activeTextColor: Color
)

object CarButtonDefaults {
    val colors: CarButtonColors
        @Composable get () = CarButtonColors(
            backgroundBrush = buildGradientBrush(CarTheme.carColors.secondarySurface),
            activeBrush = buildGradientBrush(CarTheme.carColors.accentContainer),
            textColor = CarTheme.carColors.onSurface,
            activeTextColor = CarTheme.carColors.onAccentContainer
        )
    val shape: Shape
        @Composable get() = RoundedCornerShape(CarTheme.carDimensions.buttonRadiusPercent)
}

@Composable
fun CarButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    active: Boolean = false,
    colors: CarButtonColors = CarButtonDefaults.colors,
    shape: Shape = CarButtonDefaults.shape,
    content: @Composable RowScope.() -> Unit
) {

    val backgroundBrush = if (active) colors.activeBrush else colors.backgroundBrush
    val foregroundColor = if (active) colors.activeTextColor else colors.textColor
    val disabledOverlay = CarTheme.carColors.disabledOverlay
    Box(
        modifier = modifier
            .heightIn(min = CarTheme.carDimensions.buttonMinHeight)
            .widthIn(min = CarTheme.carDimensions.buttonMinWidth)
            .clip(shape)
            .background(backgroundBrush)
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .drawWithContent {
                drawContent()
                if (!enabled) drawRect(color = disabledOverlay)
            },
        contentAlignment = Alignment.CenterStart
    ) {
        CompositionLocalProvider(
            LocalContentColor provides foregroundColor,
            LocalTextStyle provides CarTheme.carTypography.rowTitle
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = CarTheme.carDimensions.defaultHorizontalPadding,
                        vertical = CarTheme.carDimensions.defaultVerticalPadding
                    ),
                horizontalArrangement = Arrangement.spacedBy(CarTheme.carDimensions.defaultHorizontalPadding),
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
        if (!enabled) Box(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .width(IntrinsicSize.Max)
                .background(CarTheme.carColors.disabledOverlay)
        )
    }
}