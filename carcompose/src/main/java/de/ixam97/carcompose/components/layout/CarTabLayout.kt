package de.ixam97.carcompose.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.LocalCarColors

data class CarTabColors(
    val foregroundColor: Color,
    val selectedForegroundColor: Color,
    val backgroundBrush: Brush,
    val selectedBackgroundBrush: Brush,
    val selectedDecorLineColor: Color
)

data class CarTabDimensions(
    val decorLineThickness: Dp,
    val verticalDividerLineHeight: Dp
)

object CarTabDefaults {
    val colors: CarTabColors
        @Composable get() = CarTheme.carColors.tabColors
    val dimensions: CarTabDimensions
        @Composable get() = CarTheme.carDimensions.tabDimensions
}

object CarTabLayout {
    enum class Orientation {
        Horizontal,
        HorizontalCompact,
        Vertical,
        VerticalCompact
    }

    data class Tab<T> (
        val title: String,
        val icon: Painter,
        val iconActive: Painter? = null,
        val enabled: Boolean = true,
        val key: T
    )
}

/**
 * Basic car layout including header, tabs and content
 */
@Composable
fun <T> CarTabLayout(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    tabOrientation: CarTabLayout.Orientation = CarTabLayout.Orientation.Vertical,
    selectedKey: T,
    onTabSelected: (T) -> Unit,
    tabs: List<CarTabLayout.Tab<T>>,
    maxTabs: Int = 4,
    headerTitle: String = "",
    onBackAction: (() -> Unit)? = null,
    headerStartContent: @Composable (() -> Unit)? = null,
    headerContent: @Composable () -> Unit = { },
    headerEndContent: @Composable (() -> Unit)? = null,
    headerIconButtons: List<@Composable ()-> Unit> = emptyList<@Composable ()-> Unit>(),
    content: @Composable (T) -> Unit
) {

    val showDivider: Boolean
        = (tabOrientation == CarTabLayout.Orientation.Vertical || tabOrientation == CarTabLayout.Orientation.VerticalCompact)
            || (
                !CarTheme.carUiProperties.headerDividerBelowTabLayout
                && (tabOrientation == CarTabLayout.Orientation.Horizontal || tabOrientation == CarTabLayout.Orientation.HorizontalCompact)
            )

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize(),
            color = LocalCarColors.current.background,
            contentColor = LocalCarColors.current.onBackground
        ) {
            Column {
                CarHeader(
                    isLoading = isLoading,
                    title = headerTitle,
                    onBackAction = onBackAction,
                    leadingContent = headerStartContent,
                    content = { headerContent() },
                    trailingContent = headerEndContent,
                    iconButtons = headerIconButtons,
                    showDivider = showDivider
                )
                CarTabLayout(
                    orientation = tabOrientation,
                    selectedKey = selectedKey,
                    onTabSelected = onTabSelected,
                    isLoading = isLoading,
                    tabs = tabs,
                    maxTabs = maxTabs,
                    content = content
                )
            }
        }
    }
}

@Composable
private fun <T> CarTabLayout(
    orientation: CarTabLayout.Orientation = CarTabLayout.Orientation.Vertical,
    isLoading: Boolean = false,
    showDivider: Boolean = CarTheme.carUiProperties.headerDividerBelowTabLayout,
    tabs: List<CarTabLayout.Tab<T>>,
    maxTabs: Int,
    selectedKey: T,
    onTabSelected: (T) -> Unit,
    colors: CarTabColors = CarTabDefaults.colors,
    dimensions: CarTabDimensions = CarTabDefaults.dimensions,
    content: @Composable (T) -> Unit

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
                        .verticalScroll(rememberScrollState())
                ) {
                    tabs.subList(0, tabs.size.coerceAtMost(maxTabs)).forEach { tab ->
                        CarTabLayoutVerticalTab(
                            selected = selectedKey == tab.key, // selectedTabIndex == index,
                            title = tab.title,
                            icon = tab.icon,
                            iconActive = tab.iconActive,
                            enabled = tab.enabled,
                            colors = colors,
                            dimensions = dimensions
                        ) {
                            onTabSelected(tab.key)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensions.verticalDividerLineHeight)
                                .padding(start = CarTheme.carDimensions.defaultHorizontalPadding)
                                .background(brush = CarTheme.carColors.secondaryDivider)
                        )
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content(selectedKey)
                }
            }
        }
        CarTabLayout.Orientation.VerticalCompact -> {
            Row {
                Column(
                    modifier = Modifier
                        .padding(
                            // start = CarTheme.carDimensions.defaultHorizontalPadding,
                            top = CarTheme.carDimensions.defaultVerticalPadding
                        )
                        .width(IntrinsicSize.Max)
                        .verticalScroll(rememberScrollState())
                ) {
                    tabs.subList(0, tabs.size.coerceAtMost(maxTabs)).forEach { tab ->
                        CarTabLayoutVerticalTabCompact(
                            selected = selectedKey == tab.key, // selectedTabIndex == index,
                            title = tab.title,
                            icon = tab.icon,
                            iconActive = tab.iconActive,
                            enabled = tab.enabled,
                            colors = colors,
                            dimensions = dimensions
                        ) {
                            onTabSelected(tab.key)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensions.verticalDividerLineHeight)
                                .padding(start = CarTheme.carDimensions.defaultHorizontalPadding)
                                .background(brush = CarTheme.carColors.secondaryDivider)
                        )
                    }
                }
                Box(
                    modifier = Modifier.weight(1f)//fillMaxSize()
                ) {
                    content(selectedKey)
                }
            }
        }

        CarTabLayout.Orientation.Horizontal -> {
            Column {
                Row(
                    modifier = Modifier
                        .height(156.dp)
                ) {
                    tabs.subList(0, tabs.size.coerceAtMost(maxTabs)).forEach { tab ->
                        CarTabLayoutHorizontalTab(
                            modifier = Modifier.weight(1f),
                            selected = selectedKey == tab.key,
                            title = tab.title,
                            icon = tab.icon,
                            iconActive = tab.iconActive,
                            enabled = tab.enabled,
                            colors = colors,
                            dimensions = dimensions
                        ) {
                            onTabSelected(tab.key)
                        }
                    }
                }
                if (showDivider) CarHeaderDivider(isLoading)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    // contentAlignment = Alignment.TopCenter
                ) {
                    content(selectedKey)
                }
            }
        }
        CarTabLayout.Orientation.HorizontalCompact -> {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    tabs.subList(0, tabs.size.coerceAtMost(maxTabs)).forEach { tab ->
                        CarTabLayoutHorizontalTabCompact(
                            modifier = Modifier.weight(1f),
                            selected = selectedKey == tab.key,
                            title = tab.title,
                            icon = tab.icon,
                            iconActive = tab.iconActive,
                            enabled = tab.enabled,
                            colors = colors,
                            dimensions = dimensions
                        ) {
                            onTabSelected(tab.key)
                        }
                    }
                }
                if (showDivider) CarHeaderDivider(isLoading)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    // contentAlignment = Alignment.TopCenter
                ) {
                    content(selectedKey)
                }
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
    colors: CarTabColors,
    dimensions: CarTabDimensions,
    onClick: () -> Unit
) {
    val foregroundColor = if (selected) {
        colors.selectedForegroundColor
    } else {
        colors.foregroundColor
    }.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .background(if (selected) colors.selectedBackgroundBrush else colors.backgroundBrush)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = CarTheme.carDimensions.defaultHorizontalPadding),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(vertical = CarTheme.carDimensions.defaultVerticalPadding)
                    .size(CarTheme.carDimensions.iconButtonSize),
                painter = if (selected) iconActive ?: icon else icon,
                contentDescription = null,
                tint = foregroundColor
            )
            Text(
                text = title,
                style = CarTheme.carTypography.rowTitle,
                color = foregroundColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(dimensions.decorLineThickness)
                .background(if (selected) colors.selectedDecorLineColor else Color.Transparent)
        )
    }
}

@Composable
private fun CarTabLayoutVerticalTabCompact(
    selected: Boolean,
    title: String,
    icon: Painter,
    iconActive: Painter? = null,
    enabled: Boolean = true,
    colors: CarTabColors,
    dimensions: CarTabDimensions,
    onClick: () -> Unit
) {
    val foregroundColor = if (selected) {
        colors.selectedForegroundColor
    } else {
        colors.foregroundColor
    }.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(min = 400.dp)
            .height(CarTheme.carDimensions.rowMinHeight + CarTheme.carDimensions.defaultVerticalPadding * 2)
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .background(if (selected) colors.selectedBackgroundBrush else colors.backgroundBrush),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = CarTheme.carDimensions.defaultHorizontalPadding)
                .size(CarTheme.carDimensions.iconButtonSize),
            painter = if (selected) iconActive ?: icon else icon,
            contentDescription = null,
            tint = foregroundColor
        )
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = CarTheme.carTypography.rowTitle,
            color = foregroundColor
        )
        Box(
            modifier = Modifier
                .padding(start = CarTheme.carDimensions.defaultHorizontalPadding)
                .width(dimensions.decorLineThickness)
                .fillMaxHeight()
                .background(if (selected) colors.selectedDecorLineColor else Color.Transparent)
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
    colors: CarTabColors,
    dimensions: CarTabDimensions,
    onClick: () -> Unit
) {
    val foregroundColor = if (selected) {
        colors.selectedForegroundColor
    } else {
        colors.foregroundColor
    }.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))
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
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .height(dimensions.decorLineThickness)
                    .fillMaxWidth()
                    .background(if (selected) colors.selectedDecorLineColor else Color.Transparent)
            )
        }

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
    colors: CarTabColors,
    dimensions: CarTabDimensions,
    onClick: () -> Unit
) {
    val foregroundColor = if (selected) {
        colors.selectedForegroundColor
    } else {
        colors.foregroundColor
    }.copy(alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha)

    Box(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier.padding(vertical = CarTheme.carDimensions.defaultVerticalPadding * 1.5f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = CarTheme.carDimensions.defaultHorizontalPadding)
                        .size(CarTheme.carDimensions.iconButtonSize * 0.8f),
                    painter = if (selected) iconActive ?: icon else icon,
                    contentDescription = null,
                    tint = foregroundColor
                )
                Text(
                    text = title,
                    style = CarTheme.carTypography.rowTitle,
                    color = foregroundColor
                )
            }
            Box(
                modifier = Modifier
                    .height(dimensions.decorLineThickness)
                    .fillMaxWidth()
                    .background(if (selected) colors.selectedDecorLineColor else Color.Transparent)
            )
        }
    }
}