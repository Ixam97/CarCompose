package de.ixam97.carcompose.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.LocalCarDimensions
import de.ixam97.carcompose.theme.LocalCarUiProperties

@Composable
fun CarHeader(
    isLoading: Boolean = false,
    showDivider: Boolean = true,
    title: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    iconButtons: List<@Composable ()-> Unit> = emptyList<@Composable ()-> Unit>(),
    content: @Composable (RowScope.() -> Unit) = { },
) {
    Column {
        // Header content
        Box(
            modifier = Modifier
                .height(LocalCarDimensions.current.headerHeight)
                .fillMaxWidth()
                .padding(horizontal = LocalCarDimensions.current.headerContentHorizontalPadding),
            contentAlignment = LocalCarUiProperties.current.headerContentAlignment
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .height(67.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingContent != null) {
                    leadingContent()
                    Spacer(Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
                }

                if (title != null) {
                    Text(
                        text = title,
                        style = CarTheme.carTypography.title
                    )
                } else content()

                Spacer(Modifier.weight(1f))

                if (trailingContent != null && iconButtons.isEmpty()) {
                    trailingContent()
                } else if (iconButtons.isNotEmpty()) {
                    iconButtons.reversed().forEachIndexed { index, iconButton ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = CarTheme.carDimensions.defaultHorizontalPadding / 2)
                        ) {
                            iconButton()
                        }
                        if (index < iconButtons.size - 1 && CarTheme.carUiProperties.headerIconButtonDividers) {
                            Box(
                                modifier = Modifier
                                    .height(19.dp)
                                    .width(2.dp)
                                    .background(CarTheme.carColors.secondaryDivider.first())
                            )
                        }
                    }
                }
            }
        }
        // Header divider
        if (showDivider) CarHeaderDivider(isLoading)
    }
}

@Composable
fun CarHeaderDivider(
    isLoading: Boolean = false
) {
    Box(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .padding(horizontal = LocalCarDimensions.current.headerDividerHorizontalPadding)
            .background(CarTheme.carColors.primaryDivider.first())
    ) {
        if (isLoading) LinearProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            color = CarTheme.carColors.accent,
            trackColor = CarTheme.carColors.secondaryDivider.first()
        )
    }
}