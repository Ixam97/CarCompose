package de.ixam97.carcompose.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.LocalCarColors
import de.ixam97.carcompose.utils.buildGradientBrush

object CarTabLayout {
    enum class Orientation {
        Horizontal,
        HorizontalCompact,
        Vertical
    }

    data class Tab (
        val title: String,
        val icon: Painter,
        val iconActive: Painter? = null,
        val enabled: Boolean = true
    )
}

/**
 * Basic car layout including header, tabs and content
 */
@Composable
fun CarTabLayout(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    tabOrientation: CarTabLayout.Orientation = CarTabLayout.Orientation.Vertical,
    tabSelectedIndex: Int,
    tabOnIndexChanged: (Int) -> Unit,
    tabs: List<CarTabLayout.Tab>,
    headerTitle: String = "",
    headerStartContent: @Composable (() -> Unit)? = null,
    headerContent: @Composable () -> Unit = { },
    headerEndContent: @Composable (() -> Unit)? = null,
    headerIconButtons: List<@Composable ()-> Unit> = emptyList<@Composable ()-> Unit>(),
    content: @Composable () -> Unit
) {

    val showDivider: Boolean
        = (tabOrientation == CarTabLayout.Orientation.Vertical)
            || (
                !CarTheme.carUiProperties.headerDividerBelowTabLayout
                && (tabOrientation == CarTabLayout.Orientation.Horizontal || tabOrientation == CarTabLayout.Orientation.HorizontalCompact)
            )

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val insets = WindowInsets.safeDrawing.asPaddingValues()
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(insets),
            color = LocalCarColors.current.background,
            contentColor = LocalCarColors.current.onBackground
        ) {
            Column {
                CarHeader(
                    isLoading = isLoading,
                    title = headerTitle,
                    leadingContent = headerStartContent,
                    content = { headerContent() },
                    trailingContent = headerEndContent,
                    iconButtons = headerIconButtons,
                    showDivider = showDivider
                )
                CarTabLayout(
                    orientation = tabOrientation,
                    selectedTabIndex = tabSelectedIndex,
                    onTabSelected = { tabOnIndexChanged(it) },
                    isLoading = isLoading,
                    tabs = tabs
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
private fun CarTabLayout(
    orientation: CarTabLayout.Orientation = CarTabLayout.Orientation.Vertical,
    isLoading: Boolean = false,
    showDivider: Boolean = CarTheme.carUiProperties.headerDividerBelowTabLayout,
    tabs: List<CarTabLayout.Tab>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    content: @Composable () -> Unit

) {
    when (orientation) {
        CarTabLayout.Orientation.Vertical -> {
            Row {
                Column(
                    modifier = Modifier
                        .padding(
                            // start = CarTheme.carDimensions.defaultHorizontalPadding,
                            top = CarTheme.carDimensions.defaultVerticalPadding
                        )
                        .width(189.dp + CarTheme.carDimensions.defaultHorizontalPadding)
                ) {
                    tabs.subList(0, tabs.size.coerceAtMost(4)).forEachIndexed { index, tab ->
                        CarTabLayoutVerticalTab(
                            selected = selectedTabIndex == index,
                            title = tab.title,
                            icon = tab.icon,
                            iconActive = tab.iconActive,
                            enabled = tab.enabled
                        ) {
                            onTabSelected(index)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .padding(start = CarTheme.carDimensions.defaultHorizontalPadding)
                                .background(brush = buildGradientBrush(CarTheme.carColors.secondaryDivider))
                        )
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content()
                }
            }
        }

        CarTabLayout.Orientation.Horizontal -> {
            Column {
                Row(
                    modifier = Modifier
                        .height(156.dp)
                ) {
                    tabs.subList(0, tabs.size.coerceAtMost(4)).forEachIndexed { index, tab ->
                        CarTabLayoutHorizontalTab(
                            modifier = Modifier.weight(1f),
                            selected = selectedTabIndex == index,
                            title = tab.title,
                            icon = tab.icon,
                            iconActive = tab.iconActive,
                            enabled = tab.enabled
                        ) {
                            onTabSelected(index)
                        }
                    }
                }
                if (showDivider) CarHeaderDivider(isLoading)
                content()
            }
        }
        CarTabLayout.Orientation.HorizontalCompact -> {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    tabs.subList(0, tabs.size.coerceAtMost(4)).forEachIndexed { index, tab ->
                        CarTabLayoutHorizontalTabCompact(
                            modifier = Modifier.weight(1f),
                            selected = selectedTabIndex == index,
                            title = tab.title,
                            icon = tab.icon,
                            iconActive = tab.iconActive,
                            enabled = tab.enabled
                        ) {
                            onTabSelected(index)
                        }
                    }
                }
                if (showDivider) CarHeaderDivider(isLoading)
                content()
            }
        }
    }
}

@Composable
private fun CarTabLayoutVerticalTab(
    selected: Boolean,
    title: String,
    icon: Painter,
    iconActive: Painter? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val foregroundColor = if (selected) {
        CarTheme.carColors.accent
    } else {
        CarTheme.carColors.onBackground
    }.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(start = CarTheme.carDimensions.defaultHorizontalPadding),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(vertical = CarTheme.carDimensions.defaultVerticalPadding)
                .size(CarTheme.carDimensions.iconButtonSize),
            painter = if(selected) iconActive?:icon else icon,
            contentDescription = null,
            tint = foregroundColor
        )
        Text(
            text = title,
            style = CarTheme.carTypography.rowTitle,
            color = foregroundColor
        )
    }
}

@Composable
private fun CarTabLayoutHorizontalTab(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String,
    icon: Painter,
    iconActive: Painter? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val foregroundColor = if (selected) {
        CarTheme.carColors.accent
    } else {
        CarTheme.carColors.onBackground
    }.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(bottom = CarTheme.carDimensions.defaultVerticalPadding)
                .size(CarTheme.carDimensions.iconButtonSize),
            painter = if (selected) iconActive?:icon else icon,
            contentDescription = null,
            tint = foregroundColor
        )
        Text(
            text = title,
            style = CarTheme.carTypography.rowTitle,
            color = foregroundColor
        )
    }
}

@Composable
private fun CarTabLayoutHorizontalTabCompact(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String,
    icon: Painter,
    iconActive: Painter? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val foregroundColor = if (selected) {
        CarTheme.carColors.accent
    } else {
        CarTheme.carColors.onBackground
    }.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)

    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(vertical = CarTheme.carDimensions.defaultVerticalPadding * 1.5f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end = CarTheme.carDimensions.defaultHorizontalPadding)
                .size(CarTheme.carDimensions.iconButtonSize * 0.8f),
            painter = if (selected) iconActive?:icon else icon,
            contentDescription = null,
            tint = foregroundColor
        )
        Text(
            text = title,
            style = CarTheme.carTypography.rowTitle,
            color = foregroundColor
        )
    }
}