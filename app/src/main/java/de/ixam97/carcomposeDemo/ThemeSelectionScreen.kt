package de.ixam97.carcomposeDemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import de.ixam97.carcompose.components.controls.CarRow
import de.ixam97.carcompose.components.controls.CarRowCheckbox
import de.ixam97.carcompose.components.controls.CarRowRadioButton
import de.ixam97.carcompose.components.controls.CarRowSwitch
import de.ixam97.carcompose.components.controls.CarSegmentedButton
import de.ixam97.carcompose.components.layout.CarColumn
import de.ixam97.carcompose.components.layout.CarListItem
import de.ixam97.carcompose.components.layout.CarListSection
import de.ixam97.carcompose.components.layout.CarListSectionTitle
import de.ixam97.carcompose.components.layout.CarPaneLayout
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.GenericCarThemeConfig
import de.ixam97.carcompose.theme.themes.PolestarClassicThemeConfig
import de.ixam97.carcompose.theme.themes.PolestarModernThemeConfig
import de.ixam97.carcompose.theme.themes.VolvoCarUxThemeConfig
import kotlinx.serialization.Serializable

@Serializable
object ThemeSelectionScreenNavKey: NavKey

@Composable
fun ThemeSelectionScreen(
    mainViewModel: MainViewModel,
    backStack: NavBackStack<NavKey>
) {
    val themeState = mainViewModel.themeState.collectAsState()

    fun carThemeRadioButtonListItem(
        title: String,
        key: CarThemeConfig
    ): CarListItem {
        return CarListItem {
            CarRowRadioButton(
                title = title,
                isSelected = (themeState.value.carThemeConfig == key),
                onSelect = { mainViewModel.setTheme(key) },
                enabled = !themeState.value.autoDetectTheme
            )
        }
    }

    CarPaneLayout(
        headerTitle = "Theme Selection",
        onBackAction = { backStack.removeLastOrNull() }
    ) {
        CarColumn() {
            CarListSectionTitle("Theme Brightness Mode:")
            ThemeBrightnessModeSegmentedButton(
                theme = themeState.value.carThemeConfig,
                selectedMode = themeState.value.themeBrightnessMode
            ) { mainViewModel.setThemeBrightnessMode(it) }
            CarListSection(
                sectionTitle = "Built in OEM Themes:",
                listItems = listOf(
                    CarListItem {
                      CarRowSwitch(
                          title = "Auto-Select OEM Theme",
                          description = "If Car Compose comes a theme fitting to the OEM it will be selected by default. Otherwise, a fallback is used.",
                          state = themeState.value.autoDetectTheme
                      ) { mainViewModel.setAutoDetectTheme(it) }
                    },
                    CarListItem {
                        CarRowCheckbox(
                            title = "Auto-Select OEM Theme",
                            description = "If Car Compose comes a theme fitting to the OEM it will be selected by default. Otherwise, a fallback is used.",
                            isSelected = themeState.value.autoDetectTheme,
                            onSelect = { mainViewModel.setAutoDetectTheme(!themeState.value.autoDetectTheme) }
                        )
                    },
                    carThemeRadioButtonListItem(
                        title = "Generic Theme",
                        key = GenericCarThemeConfig
                    ),
                    carThemeRadioButtonListItem(
                        title = "Classic Polestar Theme",
                        key = PolestarClassicThemeConfig
                    ),
                    carThemeRadioButtonListItem(
                        title = "Modern Polestar Theme",
                        key = PolestarModernThemeConfig
                    ),
                    carThemeRadioButtonListItem(
                        title = "Volvo Car UX Theme",
                        key = VolvoCarUxThemeConfig
                    ),
                )
            )
            CarListSection(
                sectionTitle = "Custom Themes:",
                listItems = listOf(
                    carThemeRadioButtonListItem(
                        title = "Polestar Club Theme",
                        key = CustomCarThemeConfig
                    ),
                )
            )
        }
    }
}

@Composable
internal fun ThemeBrightnessModeSegmentedButton(
    theme: CarThemeConfig,
    selectedMode: ThemeBrightnessMode,
    onSelection: (ThemeBrightnessMode) -> Unit
) {
    fun themeBrightnessModeSegment(
        name: String,
        key: ThemeBrightnessMode,
        enabled: Boolean = true,
    ): CarSegmentedButton.Segment<ThemeBrightnessMode> {
        return CarSegmentedButton.Segment(
            content = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(name)
                }
            },
            key = key,
            enabled = enabled
        )
    }

    CarRow(
        content = {
            CarSegmentedButton(
                segments = listOf(
                    themeBrightnessModeSegment(
                        "Auto",
                        ThemeBrightnessMode.Auto
                    ),
                    themeBrightnessModeSegment(
                        "Bright",
                        ThemeBrightnessMode.Bright,
                        enabled = theme.carBrightColors != null
                    ),
                    themeBrightnessModeSegment(
                        "Dark",
                        ThemeBrightnessMode.Dark
                    ),
                ),
                selectedKey = selectedMode,
                onSegmentChanged = { 
                    it?.let { onSelection(it) }
                }
            )
        }
    )
}