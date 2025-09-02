package de.ixam97.carcompose.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import de.ixam97.carcompose.R

object CarIcons {
    val settings: Painter
        @Composable get() = painterResource(R.drawable.ic_settings_48)
    val arrowBack: Painter
        @Composable get() = painterResource(R.drawable.ic_arrow_backwards_48)
    val arrowForwards: Painter
        @Composable get() = painterResource(R.drawable.ic_arrow_forward_48)
    val arrowOutwards: Painter
        @Composable get() = painterResource(R.drawable.ic_arrow_outwards_48)
    val close: Painter
        @Composable get() = painterResource(R.drawable.ic_close_48)
    val grid: Painter
        @Composable get() = painterResource(R.drawable.ic_grid_48)

    object Filled {
        val grid: Painter
            @Composable get() = painterResource(R.drawable.ic_grid_filled_48)
        val settings: Painter
            @Composable get() = painterResource(R.drawable.ic_settings_filled_48)
    }
}