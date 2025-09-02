package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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
    val tint = if (active) activeTint else tint
    val painter = if (!active || activePainter == null) painter else activePainter
    Box(
        modifier = Modifier
            .size(67.dp)
            // .clip(CircleShape)
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(CarTheme.carDimensions.iconButtonSize),
            painter = painter,
            contentDescription = null,
            tint = tint.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)
        )
    }
}

@Composable
fun CarIconButton(
    imageVector: ImageVector, // painter: Painter,
    enabled: Boolean = true,
    tint: Color = CarTheme.carColors.onBackground,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(67.dp)
            // .clip(CircleShape)
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(CarTheme.carDimensions.iconButtonSize),
            imageVector = imageVector,
            contentDescription = null,
            tint = tint.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)
        )
    }
}