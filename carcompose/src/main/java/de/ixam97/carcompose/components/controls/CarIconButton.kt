package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import de.ixam97.carcompose.theme.CarTheme

@Composable
fun CarIconButton(
    painter: Painter,
    activePainter: Painter? = null,
    enabled: Boolean = true,
    active: Boolean = false,
    tint: Color = CarTheme.carColors.onBackground,
    activeTint: Color = CarTheme.carColors.accent,
    onClick: () -> Unit
) {
    val currentTint = if (active) activeTint else tint
    val currentPainter = if (!active || activePainter == null) painter else activePainter
    CarIconButtonBase(
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(CarTheme.carDimensions.iconButtonSize),
            painter = currentPainter,
            contentDescription = null,
            tint = currentTint.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)
        )
    }
}

@Composable
fun CarIconButton(
    imageVector: ImageVector, // painter: Painter,
    activeImageVector: ImageVector? = null,
    enabled: Boolean = true,
    active: Boolean = false,
    tint: Color = CarTheme.carColors.onBackground,
    activeTint: Color = CarTheme.carColors.accent,
    onClick: () -> Unit
) {
    val currentTint = if (active) activeTint else tint
    val currentImageVector = if (!active || activeImageVector == null) imageVector else activeImageVector
    CarIconButtonBase(
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(CarTheme.carDimensions.iconButtonSize),
            imageVector = currentImageVector,
            contentDescription = null,
            tint = currentTint.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)
        )
    }
}

@Composable
private fun CarIconButtonBase(
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable (() -> Unit)
) {
    Box(
        modifier = Modifier
            .size(CarTheme.carDimensions.iconButtonSize),
        contentAlignment = Alignment.Center
    ) {
        content()
        Box(
            modifier = Modifier
                .requiredSize(CarTheme.carDimensions.iconButtonSize * 1.6f)
                .clip(CircleShape)
                .clickable(
                    enabled = enabled,
                    onClick = onClick
                ),
        )
    }
}