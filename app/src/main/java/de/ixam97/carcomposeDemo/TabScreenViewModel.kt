package de.ixam97.carcomposeDemo

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowDpSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import de.ixam97.carcompose.components.layout.CarTabLayout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class TabScreenTabs {
    Tab1, Tab2, Tab3
}

enum class TabScreenLayoutType(val orientation: @Composable () -> CarTabLayout.Orientation) {
    Horizontal({ CarTabLayout.Orientation.Horizontal}),
    HorizontalCompact({ CarTabLayout.Orientation.HorizontalCompact}),
    Vertical({ CarTabLayout.Orientation.Vertical}),
    VerticalCompact({ CarTabLayout.Orientation.VerticalCompact}),
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    Auto({
        val windowDpSize = currentWindowDpSize()
        if (windowDpSize.width < 1300.dp) CarTabLayout.Orientation.Horizontal else CarTabLayout.Orientation.Vertical
    }),
}

data class TabScreenState(
    val selectedTab: TabScreenTabs = TabScreenTabs.Tab1,
    val layoutType: TabScreenLayoutType = TabScreenLayoutType.Auto
)

class TabScreenViewModel: ViewModel() {
    private var _state = MutableStateFlow(TabScreenState())
    val state = _state.asStateFlow()

    fun setSelectedTab(tab: TabScreenTabs) {
        _state.update { it.copy(
            selectedTab = tab
        ) }
    }

    fun setLayoutType(layoutType: TabScreenLayoutType) {
        _state.update { it.copy(
            layoutType = layoutType
        ) }
    }
}