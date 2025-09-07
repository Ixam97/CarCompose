package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
    leadingContent: @Composable (() -> Unit)? = null,
    descriptionContent: @Composable (ColumnScope.() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null
) {

    val foregroundColor = CarTheme.carColors.onBackground.copy(
        alpha = if (enabled) 1f else CarTheme.carColors.disabledAlpha
    )

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
           .height(IntrinsicSize.Min)
           .heightIn(min = CarTheme.carDimensions.rowMinHeight),
       verticalAlignment = Alignment.CenterVertically
    ) {

        CompositionLocalProvider(
            LocalContentColor provides foregroundColor
        ) {
            if (leadingContent != null) {
                leadingContent()
                Spacer(modifier = Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
            }
            Box(
                Modifier.weight(1f)
            ) {
                if (content != null) content()
                else if (title != null) {
                    Column {
                        Text(
                            text = title,
                            style = CarTheme.carTypography.rowTitle,
                            color = LocalContentColor.current
                        )
                        if (descriptionContent != null) {
                            Spacer(Modifier.size(CarTheme.carDimensions.defaultVerticalPadding))
                            descriptionContent()
                        }
                        else description?.let { description ->
                            Spacer(Modifier.size(CarTheme.carDimensions.rowTextSpacing))
                            Text(
                                text = description,
                                style = CarTheme.carTypography.rowContent,
                                color = LocalContentColor.current.copy(alpha =  0.7f)
                            )
                        }
                    }
                }
            }
            if (trailingContent != null) {
                Spacer(modifier = Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
                trailingContent()
            }
            if (browsable) {
                Spacer(modifier = Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = CarIcons.arrowForwards,
                    tint = LocalContentColor.current,
                    contentDescription = null
                )
            }
        }
    }
}