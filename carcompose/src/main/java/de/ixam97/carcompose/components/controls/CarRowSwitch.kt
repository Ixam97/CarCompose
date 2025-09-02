package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme

@Composable
fun CarRowSwitch(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    enabled: Boolean = true,
    state: Boolean,
    onStateChange: (newState: Boolean) -> Unit
) {
    val foregroundColor = CarTheme.carColors.onBackground
    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = { onStateChange(!state) }
            )
            .defaultMinSize(minHeight = 92.dp)
            .padding(
                vertical = CarTheme.carDimensions.defaultVerticalPadding,
                horizontal = CarTheme.carDimensions.defaultHorizontalPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column( modifier = Modifier.weight(1f) ) {
            Text(
                text = title,
                style = CarTheme.carTypography.rowTitle,
                color = foregroundColor.copy(
                    alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha
                )
            )
            description?.let { description ->
                Spacer(Modifier.size(CarTheme.carDimensions.rowTextSpacing))
                Text(
                    text = description,
                    style = CarTheme.carTypography.rowContent,
                    color = foregroundColor.copy(
                        alpha = if (enabled) 0.7f else (CarTheme.carColors.disabledAlpha - 0.1f)
                    )
                )
            }
        }
        Spacer(modifier = Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
        Column (
            modifier = Modifier.height(IntrinsicSize.Max),
            verticalArrangement = Arrangement.Top
        ) {
            // Switch(
            //     modifier = Modifier
            //         .scale(1.25f),
            //     checked = state,
            //     onCheckedChange = onStateChange
            // )
            CarBasicSwitch(
                isChecked = state,
                onCheckedChange = onStateChange,
                enabled = enabled
            )
        }
    }
}