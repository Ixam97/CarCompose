package de.ixam97.carcompose.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.ixam97.carcompose.theme.LocalCarColors

/**
 * Basic car layout including header and content
 */
@Composable
fun CarPaneLayout(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    headerTitle: String = "",
    headerStartContent: @Composable (() -> Unit)? = null,
    headerContent: @Composable () -> Unit = { },
    headerEndContent: @Composable (() -> Unit)? = null,
    headerIconButtons: List<@Composable ()-> Unit> = emptyList<@Composable ()-> Unit>(),
    content: @Composable () -> Unit
) {
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
                    iconButtons = headerIconButtons
                )
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content()
                }
            }
        }
    }
}