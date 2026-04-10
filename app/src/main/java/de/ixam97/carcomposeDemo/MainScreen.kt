package de.ixam97.carcomposeDemo

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import de.ixam97.carcompose.components.controls.CarRow
import de.ixam97.carcompose.components.layout.CarColumn
import de.ixam97.carcompose.components.layout.CarListItem
import de.ixam97.carcompose.components.layout.CarListSection
import de.ixam97.carcompose.components.layout.CarPaneLayout
import de.ixam97.carcompose.theme.CarTheme
import kotlinx.serialization.Serializable

@Serializable
object MainScreenNavKey: NavKey

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    backStack: NavBackStack<NavKey>
) {
    CarPaneLayout(
        headerTitle = "Car Compose Demo",
        headerStartContent = { AppIcon() }
    ) {
        CarColumn() {
            CarListSection(
                sectionTitle = "Info: ",
                listItems = listOf(
                    CarListItem {
                        CarRow(
                            title = "Hello Car Compose!",
                            description = "This is a demo App to showcase the capabilities of the Car Compose library.",
                            leadingContent = { AppIcon() }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "${Build.BRAND}, ${Build.DEVICE}, ${Build.MODEL}",
                            description = "Brand, Device, Model",
                            leadingContent = {
                                Icon(
                                    modifier = Modifier.size(CarTheme.carDimensions.iconButtonSize),
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null
                                )
                            }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "Theme selection",
                            description = "Select the built in themes or a custom theme",
                            browsable = true,
                            onBrowse = { backStack.add(ThemeSelectionScreenNavKey) },
                            leadingContent = {
                                Icon(
                                    modifier = Modifier.size(CarTheme.carDimensions.iconButtonSize),
                                    imageVector = Icons.Outlined.Palette,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                )
            )
            CarListSection(
                sectionTitle = "Layouts:",
                listItems = listOf(
                    CarListItem {
                        CarRow(
                            title = "List Layout",
                            browsable = true,
                            onBrowse = { }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "Grid Layout",
                            browsable = true,
                            onBrowse = { }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "Mixed Layout",
                            browsable = true,
                            onBrowse = { backStack.add(TabScreenSettingsScreenNavKey) }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "Tab Layout",
                            browsable = true,
                            onBrowse = { backStack.add(TabScreenNavKey) }
                        )
                    },
                )
            )

            CarListSection(
                sectionTitle = "Control Elements:",
                listItems = listOf(
                    CarListItem {
                        CarRow(
                            title = "Buttons",
                            browsable = true,
                            onBrowse = { }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "Segmented Buttons",
                            browsable = true,
                            onBrowse = { }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "Switches",
                            browsable = true,
                            onBrowse = { }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "Radio Buttons",
                            browsable = true,
                            onBrowse = { }
                        )
                    },
                    CarListItem {
                        CarRow(
                            title = "Text Input",
                            browsable = true,
                            onBrowse = { }
                        )
                    },
                )
            )
        }
    }
}

@Composable
fun AppIcon() {
    Image(
        modifier = Modifier
            .size(CarTheme.carDimensions.iconButtonSize),
        painter = adaptiveIconPainterResource(R.drawable.ic_launcher),
        contentDescription = null
    )
}