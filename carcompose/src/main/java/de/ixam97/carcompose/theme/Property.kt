package de.ixam97.carcompose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment

@Immutable
data class CarUiProperties(
    val uiType: UiType = UiType.Generic,
    val headerContentAlignment: Alignment = Alignment.BottomStart,
    val headerIconButtonDividers: Boolean = true,
    val headerDividerBelowTabLayout: Boolean = true
)

val GenericCarUiProperties = CarUiProperties()

val LocalCarUiProperties = staticCompositionLocalOf {
    GenericCarUiProperties
}