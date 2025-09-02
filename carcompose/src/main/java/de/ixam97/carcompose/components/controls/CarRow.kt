package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.resources.CarIcons
import de.ixam97.carcompose.theme.CarTheme

@Composable
fun CarRow(
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    browsable: Boolean = false,
    enabled: Boolean = true,
    onBrowse: () -> Unit = {},
    content: @Composable (() -> Unit)? = null
) {
    Row(
       modifier = modifier
           .clickable(
               enabled = browsable && enabled,
               onClick = onBrowse
           )
           .padding(
               horizontal = CarTheme.carDimensions.defaultHorizontalPadding,
               vertical = CarTheme.carDimensions.defaultVerticalPadding
           )
           .heightIn(min = CarTheme.carDimensions.rowMinHeight),
       verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.weight(1f)
        ) {
            if (content != null) content()
            else if (title != null) {
                Column {
                    Text(
                        text = title,
                        style = CarTheme.carTypography.rowTitle,
                        color = CarTheme.carColors.onBackground.copy(
                            alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha
                        )
                    )
                    description?.let { description ->
                        Spacer(Modifier.size(CarTheme.carDimensions.rowTextSpacing))
                        Text(
                            text = description,
                            style = CarTheme.carTypography.rowContent,
                            color = CarTheme.carColors.onBackground.copy(
                                alpha = if (enabled) 0.7f else (CarTheme.carColors.disabledAlpha - 0.1f)
                            )
                        )
                    }
                }
            }
        }
        if (browsable) {
            Spacer(modifier = Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
            Icon(
                modifier = Modifier
                    .size(48.dp),
                painter = CarIcons.arrowForwards,
                tint = CarTheme.carColors.onBackground.copy(
                    alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha
                ),
                contentDescription = null
            )
        }
    }
}