package de.ixam97.carcompose.utils

import android.os.Build
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun calculateWindowInsets(): PaddingValues {

    val polestar4Flag = Build.MODEL == "PS4" || Build.DEVICE == "lemon_x86_64"

    //TODO: This is a workaround to take into account the system bar in Polestar 4 not being hidden
    //      when the keyboard is visible.
    if (polestar4Flag && WindowInsets.isImeVisible) {
        val imePaddingValues = WindowInsets.ime.asPaddingValues()
        val systemBarPaddingValues = WindowInsets.systemBars.asPaddingValues()
        val layoutDirection = LocalLayoutDirection.current

        return PaddingValues(
            start = systemBarPaddingValues.calculateStartPadding(layoutDirection),
            end = systemBarPaddingValues.calculateEndPadding(layoutDirection),
            top = systemBarPaddingValues.calculateTopPadding(),
            bottom = (systemBarPaddingValues.calculateBottomPadding() + imePaddingValues.calculateBottomPadding()).coerceAtLeast(0.dp)
        )
    }

    return WindowInsets.safeDrawing.asPaddingValues()
}