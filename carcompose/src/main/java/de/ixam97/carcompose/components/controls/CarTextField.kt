package de.ixam97.carcompose.components.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.buildGradientBrush

data class CarTextFieldColors(
    val backgroundBrush: Brush,
    val textColor: Color,
    val placeholderTextColor: Color,
    val cursorColor: Color,
)

data class CarTextFieldDimensions(
    val contentVerticalPadding: Dp,
    val contentHorizontalPadding: Dp
)

object CarTextFieldDefaults {
    val colors: CarTextFieldColors
        @Composable get() = CarTextFieldColors(
            backgroundBrush = buildGradientBrush(CarTheme.carColors.textFieldBackground),
            textColor = CarTheme.carColors.textFieldTextColor,
            placeholderTextColor = CarTheme.carColors.textFieldTextColor.copy(CarTheme.carColors.disabledAlpha),
            cursorColor = CarTheme.carColors.accent
        )
    val dimensions: CarTextFieldDimensions
        @Composable get() = CarTextFieldDimensions(
            contentVerticalPadding = CarTheme.carDimensions.defaultVerticalPadding,
            contentHorizontalPadding = CarTheme.carDimensions.defaultHorizontalPadding
        )
    val shape: Shape
        @Composable get() = RoundedCornerShape(CarTheme.carDimensions.buttonMinHeight * (CarTheme.carDimensions.buttonRadiusPercent / 1000f))
}

@Composable
fun CarTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = CarTheme.carTypography.rowTitle,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    colors: CarTextFieldColors = CarTextFieldDefaults.colors,
    dimensions: CarTextFieldDimensions = CarTextFieldDefaults.dimensions,
    shape: Shape = CarTextFieldDefaults.shape
) {

    val mainTextColor = if (enabled) colors.textColor else colors.textColor.copy(CarTheme.carColors.disabledAlpha)

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle.copy(color = mainTextColor),
        cursorBrush = SolidColor(colors.cursorColor),
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .clip(shape)
                    .background(colors.backgroundBrush)
                    .padding(
                        horizontal = dimensions.contentHorizontalPadding,
                        vertical = dimensions.contentVerticalPadding
                    ),
                contentAlignment = Alignment.TopStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    leadingIcon?.let {
                        it()
                        Spacer(Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty())
                            Text(
                                text = placeholder,
                                style = textStyle.copy(color = colors.placeholderTextColor)
                            )
                        innerTextField()
                    }
                    trailingIcon?.let {
                        Spacer(Modifier.size(CarTheme.carDimensions.defaultHorizontalPadding))
                        it()
                    }
                }
            }
        }
    )
}