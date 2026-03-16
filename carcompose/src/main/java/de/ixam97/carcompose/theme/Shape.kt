package de.ixam97.carcompose.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val GenericCarShapes = CarShapes(
    buttonCornerSize = CornerSize(50),
    segmentedButtonOuterCornerSize = CornerSize(50),
    segmentedButtonInnerCornerSize = CornerSize(8.dp),
    textFieldShape = RoundedCornerShape(8.dp)
)

@Immutable
data class CarShapes(
    val buttonCornerSize: CornerSize,
    val buttonShape: Shape = RoundedCornerShape(buttonCornerSize),
    val segmentedButtonOuterCornerSize: CornerSize = buttonCornerSize,
    val segmentedButtonInnerCornerSize: CornerSize = segmentedButtonOuterCornerSize,
    val segmentedButtonBackgroundCornerSize: CornerSize = segmentedButtonOuterCornerSize,
    val textFieldShape: Shape = RoundedCornerShape(segmentedButtonInnerCornerSize),
    val switchTrackShape: Shape = buttonShape,
    val switchThumbShape: Shape = switchTrackShape
)

val LocalCarShapes = staticCompositionLocalOf {
    GenericCarShapes
}