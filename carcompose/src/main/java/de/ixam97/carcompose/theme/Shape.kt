package de.ixam97.carcompose.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.components.controls.CarRadioButtonShapes
import de.ixam97.carcompose.components.controls.CarSegmentedButtonShapes
import de.ixam97.carcompose.components.controls.CarSwitchShapes

val GenericCarShapes = CarShapes(
    defaultOuterCornerSize = CornerSize(50),
    defaultInnerCornerSize = CornerSize(8.dp),
)

@Immutable
data class CarShapes(
    val defaultOuterCornerSize: CornerSize,
    val defaultInnerCornerSize: CornerSize = defaultOuterCornerSize,
    val buttonShape: Shape = RoundedCornerShape(defaultOuterCornerSize),
    val textFieldShape: Shape = RoundedCornerShape(defaultInnerCornerSize),
    val switchShapes: CarSwitchShapes = CarSwitchShapes(
        track = RoundedCornerShape(defaultOuterCornerSize),
        thumb = RoundedCornerShape(defaultInnerCornerSize)
    ),
    val radioButtonShapes: CarRadioButtonShapes = CarRadioButtonShapes(
        outerShape = RoundedCornerShape(defaultOuterCornerSize)
    ),
    val checkboxShapes: CarRadioButtonShapes = CarRadioButtonShapes(
        outerShape = RoundedCornerShape(defaultInnerCornerSize)
    ),
    val segmentedButtonShapes: CarSegmentedButtonShapes = CarSegmentedButtonShapes(
        backgroundCornerSize = defaultOuterCornerSize,
        outerCornerSize = defaultOuterCornerSize,
        innerCornerSize = defaultInnerCornerSize
    )
)

val LocalCarShapes = staticCompositionLocalOf {
    GenericCarShapes
}