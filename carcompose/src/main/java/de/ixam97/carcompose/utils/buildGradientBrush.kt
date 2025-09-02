package de.ixam97.carcompose.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun buildGradientBrush(colors: List<Color>) : Brush {
    if (colors.isEmpty()) {
        throw Exception("Colors list for gradient brush cannot be empty!")
    }

    return if (colors.size == 1) {
        Brush.linearGradient(listOf(colors.first(), colors.first()))
    } else {
        Brush.linearGradient(colors)
    }
}