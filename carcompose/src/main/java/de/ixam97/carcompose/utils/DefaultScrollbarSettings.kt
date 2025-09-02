package de.ixam97.carcompose.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import my.nanihadesuka.compose.ScrollbarSettings

val DefaultScrollbarSettings = ScrollbarSettings(
    scrollbarPadding = 4.dp,
    thumbUnselectedColor = Color.White.copy(alpha = 0.5f),
    thumbSelectedColor = Color.White,
    thumbThickness = 6.dp,
    hideDisplacement = 0.dp,
    thumbShape = RectangleShape,
    alwaysShowScrollbar = true
)