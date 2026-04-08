package de.ixam97.carcompose.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.DefaultScrollbarSettings
import my.nanihadesuka.compose.ColumnScrollbar
import my.nanihadesuka.compose.LazyColumnScrollbar
import my.nanihadesuka.compose.ScrollbarSettings

@Composable
fun CarColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    val state = rememberScrollState()
    val carScrollbarSettings = carScrollbarSettings()
    val isScrollable = rememberUpdatedState(state.canScrollForward || state.canScrollBackward)
    val scrollbarSettings by rememberUpdatedState {
        carScrollbarSettings.copy(enabled = isScrollable.value)
    }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .widthIn(max = CarTheme.carDimensions.columnDefaultMaxWidth),
    ) {
        ColumnScrollbar(
            modifier = Modifier.fillMaxSize(),
            state = state,
            settings = scrollbarSettings()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(state),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                content = content
            )
        }
    }
}

@Composable
fun CarLazyColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit
) {
    val state = rememberLazyListState()
    val carScrollbarSettings = carScrollbarSettings()
    val isScrollable = rememberUpdatedState(state.canScrollForward || state.canScrollBackward)

    val scrollbarSettings by rememberUpdatedState {
        carScrollbarSettings.copy(enabled = isScrollable.value)
    }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .widthIn(max = CarTheme.carDimensions.columnDefaultMaxWidth),
    ) {
        LazyColumnScrollbar(
            modifier = Modifier.fillMaxSize(),
            state = state,
            settings = scrollbarSettings()
        ) {
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                state = state,
                content = content
            )
        }
    }
}

@Composable
internal fun carScrollbarSettings() : ScrollbarSettings {

    val thumbSelectedColor = CarTheme.carColors.accent
    val thumbUnselectedColor = CarTheme.carColors.onSurface.copy(alpha = 0.7f)
    val thumbShape = CarTheme.carShapes.buttonShape

    return DefaultScrollbarSettings.copy(
        alwaysShowScrollbar = false,
        thumbSelectedColor = thumbSelectedColor,
        thumbUnselectedColor = thumbUnselectedColor,
        thumbShape = thumbShape
    )
}