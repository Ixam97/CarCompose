package de.ixam97.carcompose.components.layout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.themes.cinnabarRed
import de.ixam97.carcompose.utils.buildGradientBrush
import kotlinx.coroutines.delay

val LocalCarSnackBarState = staticCompositionLocalOf {
    CarSnackBarHostState()
}

data class CarSnackBarConfig(
    val identifier: String,
    val content: @Composable (() -> Unit),
    val painter: Painter? = null,
    val isError: Boolean = false,
    val actionText: String? = null,
    val onAction: () -> Unit = {},
    val duration: Int? = 3000,
    val progress: Float? = null,
)

class CarSnackBarHostState {

    var currentCarSnackBarConfigs = mutableStateListOf<CarSnackBarConfig>()
        private set

    fun showSnackBar(config: CarSnackBarConfig) {
        currentCarSnackBarConfigs.removeIf { it.identifier == config.identifier }
        currentCarSnackBarConfigs.add(config)
    }

    fun cancelSnackBar(identifier: String) {
        currentCarSnackBarConfigs.removeIf { it.identifier == identifier }
    }

    fun isSnackBarVisible(identifier: String): Boolean = currentCarSnackBarConfigs.any { it.identifier == identifier }
}

@Composable
fun CarSnackBarHost(
    hostState: CarSnackBarHostState,
    modifier: Modifier,
    carSnackBar: @Composable ((CarSnackBarConfig) -> Unit) = { CarSnackBar(it) }
) {
    val currentCarSnackBarConfigs = hostState.currentCarSnackBarConfigs

    LazyColumn(
        modifier = modifier
            .width(CarTheme.carDimensions.columnDefaultMaxWidth),
        reverseLayout = true
    ) {
        items(
            items = currentCarSnackBarConfigs,
            key = { it.identifier }
        ) { snackBarConfig ->
            Box(Modifier.animateItem()) {
                if (snackBarConfig.duration != null && snackBarConfig.duration > 0) {
                    LaunchedEffect(snackBarConfig) {
                        delay(snackBarConfig.duration.toLong())
                        hostState.cancelSnackBar(snackBarConfig.identifier)
                    }
                }
                carSnackBar(snackBarConfig)
            }
        }
    }
}

@Composable
fun CarSnackBar(
    config: CarSnackBarConfig
) {

    val animationTargetValue = remember { Animatable(initialValue = 0f) }

    val actionColor = if (config.isError) cinnabarRed else CarTheme.carColors.snackBarAccent // parisDaisy
    val backgroundColor = buildGradientBrush(if (config.isError) listOf(Color(0xFF1C0D0E)) else CarTheme.carColors.snackBarBackground) // Color(0xFF111922)

    LaunchedEffect(config) {
        if (config.duration != null && config.duration > 0) {
            animationTargetValue.snapTo(0f)
            animationTargetValue.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = config.duration))
        } else if (config.progress != null) {
            animationTargetValue.snapTo(config.progress)
        }
    }

    Box(
        modifier = Modifier
            .padding(
                horizontal = CarTheme.carDimensions.defaultHorizontalPadding * 0.5f,
                vertical = CarTheme.carDimensions.defaultVerticalPadding * 0.5f
            )
            .dropShadow(
                shape = RoundedCornerShape(20.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 6.dp,
                    color = Color(0x40000000),
                    offset = DpOffset(x = 4.dp, 4.dp)
                )
            )
            .background(backgroundColor)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides CarTheme.carColors.snackBarForeground,
            LocalTextStyle provides CarTheme.carTypography.rowTitle
        ) {
            Box() {
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .defaultMinSize(minHeight = 120.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    config.painter?.let { painter ->
                        Icon(
                            modifier = Modifier
                                .padding(vertical = CarTheme.carDimensions.defaultVerticalPadding)
                                .padding(start = CarTheme.carDimensions.defaultHorizontalPadding)
                                .size(CarTheme.carDimensions.iconButtonSize),
                            painter = painter,
                            contentDescription = null
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(
                                vertical = CarTheme.carDimensions.defaultVerticalPadding,
                                horizontal = CarTheme.carDimensions.defaultHorizontalPadding
                            ),
                        content = { config.content() }
                    )
                    Spacer(Modifier.weight(1f))
                    config.actionText?.let { actionText ->
                        Box(
                            modifier = Modifier
                                .clickable(onClick = config.onAction)
                                .fillMaxHeight()
                                .padding(
                                    vertical = CarTheme.carDimensions.defaultVerticalPadding,
                                    horizontal = CarTheme.carDimensions.defaultHorizontalPadding
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = actionText,
                                style = CarTheme.carTypography.rowTitle,
                                color = actionColor
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animationTargetValue.value)
                        .height(8.dp)
                        .align(Alignment.BottomStart)
                        .background(actionColor),
                )
            }
        }
    }
}