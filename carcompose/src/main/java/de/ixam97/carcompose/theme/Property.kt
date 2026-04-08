package de.ixam97.carcompose.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import de.ixam97.carcompose.R

class CarNavIconStyle(@DrawableRes resId:  Int) {

    private val _resId = resId

    val painter: Painter
        @Composable get() = painterResource(_resId)

    companion object {
        val ArrowBackwards = CarNavIconStyle(R.drawable.ic_arrow_backwards_48)
        val ChevronBackwards = CarNavIconStyle(R.drawable.ic_chevron_backwards_48)
        val ChevronBackwardsSmall = CarNavIconStyle(R.drawable.ic_chevron_backwards_small_48)
        val ArrowForwards = CarNavIconStyle(R.drawable.ic_arrow_forwards_48)
        val ChevronForwards = CarNavIconStyle(R.drawable.ic_chevron_forwards_48)
        val ChevronForwardsSmall = CarNavIconStyle(R.drawable.ic_chevron_forwards_small_48)
    }
}

@Immutable
data class CarUiProperties(
    val headerContentAlignment: Alignment = Alignment.BottomStart,
    val headerIconButtonDividers: Boolean = true,
    val headerDividerBelowTabLayout: Boolean = true,
    val listSectionBackground: Boolean = false,
    val backButtonIconStyle: CarNavIconStyle = CarNavIconStyle.ArrowBackwards,
    val rowBrowseIconStyle: CarNavIconStyle = CarNavIconStyle.ArrowForwards
)

val GenericCarUiProperties = CarUiProperties()

val LocalCarUiProperties = staticCompositionLocalOf {
    GenericCarUiProperties
}