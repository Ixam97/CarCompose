package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CarRowRadioButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String? = null,
    description: String? = null,
    isSelected: Boolean,
    onSelect: () -> Unit,
    colors: CarRadioButtonColors = CarRadioButtonDefaults.colors,
    shapes: CarRadioButtonShapes = CarRadioButtonDefaults.shapes,
    dimensions: CarRadioButtonDimensions = CarRadioButtonDefaults.dimensions,
    leadingContent: @Composable (() -> Unit)? = null,
    descriptionContent: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
) {
    CarRow(
        modifier = modifier,
        enabled = enabled,
        browsable = true,
        browsableType = CarRowBrowsableType.Hidden,
        onBrowse = onSelect,
        title = title,
        description = description,
        leadingContent = leadingContent,
        descriptionContent = descriptionContent,
        content = content,
        trailingContent = {
            CarBasicRadioButton(
                isSelected = isSelected,
                enabled = enabled,
                onSelect = null,
                colors = colors,
                shapes = shapes,
                dimensions = dimensions
            )
        }
    )
}