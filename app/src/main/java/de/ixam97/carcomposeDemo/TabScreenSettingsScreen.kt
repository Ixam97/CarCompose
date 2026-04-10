package de.ixam97.carcomposeDemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import de.ixam97.carcompose.components.controls.CarRowRadioButton
import de.ixam97.carcompose.components.layout.CarColumn
import de.ixam97.carcompose.components.layout.CarListItem
import de.ixam97.carcompose.components.layout.CarListSection
import de.ixam97.carcompose.components.layout.CarPaneLayout
import kotlinx.serialization.Serializable

@Serializable
data object TabScreenSettingsScreenNavKey : NavKey

@Composable
fun TabScreenSettingsScreen(
    viewModel: TabScreenViewModel,
    backStack: NavBackStack<NavKey>
) {
    val state = viewModel.state.collectAsState()

    fun tabTypeRadioButtonListItem(
        title: String,
        key: TabScreenLayoutType
    ): CarListItem {
        return CarListItem {
            CarRowRadioButton(
                title = title,
                isSelected = (state.value.layoutType == key),
                onSelect = {
                    viewModel.setLayoutType(key)
                    backStack.removeLastOrNull()
                }
            )
        }
    }


    CarPaneLayout(
        headerTitle = "Tab Layout Type",
        onBackAction = { backStack.removeLastOrNull() }
    ) {
        CarColumn() {
            CarListSection(
                sectionTitle = "Tab Layout Type:",
                listItems = listOf(
                    tabTypeRadioButtonListItem(
                        title = "Auto",
                        key = TabScreenLayoutType.Auto,
                    ),
                    tabTypeRadioButtonListItem(
                        title = "Horizontal",
                        key = TabScreenLayoutType.Horizontal,
                    ),
                    tabTypeRadioButtonListItem(
                        title = "Horizontal (Compact)",
                        key = TabScreenLayoutType.HorizontalCompact,
                    ),
                    tabTypeRadioButtonListItem(
                        title = "Vertical",
                        key = TabScreenLayoutType.Vertical,
                    ),
                    tabTypeRadioButtonListItem(
                        title = "Vertical (Compact)",
                        key = TabScreenLayoutType.VerticalCompact,
                    ),
                )
            )
        }
    }
}