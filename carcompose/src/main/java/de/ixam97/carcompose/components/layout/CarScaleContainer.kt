package de.ixam97.carcompose.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import de.ixam97.carcompose.theme.CarTheme

@Composable
fun CarScaleContainer(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)
){
    Box(
        modifier = modifier
    ) {
        BoxWithConstraints (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(maxWidth / CarTheme.SCALING_FACTOR)
                    .height(maxHeight / CarTheme.SCALING_FACTOR)
                    .scale(CarTheme.SCALING_FACTOR),
                content = { content() }
            )
        }
    }
}