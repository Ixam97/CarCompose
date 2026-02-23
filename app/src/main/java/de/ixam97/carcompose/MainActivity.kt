package de.ixam97.carcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.components.HeaderIconDummy
import de.ixam97.carcompose.components.controls.CarButtonDefaults
import de.ixam97.carcompose.components.controls.CarIconButton
import de.ixam97.carcompose.components.controls.CarRow
import de.ixam97.carcompose.components.controls.CarRowSwitch
import de.ixam97.carcompose.components.controls.CarSegmentedButton
import de.ixam97.carcompose.components.controls.CarTextField
import de.ixam97.carcompose.components.layout.CarColumn
import de.ixam97.carcompose.components.layout.CarListDivider
import de.ixam97.carcompose.components.layout.CarTabLayout
import de.ixam97.carcompose.resources.CarIcons
import de.ixam97.carcompose.theme.CarComposeTheme
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.UiTheme



enum class MainTabKeys {
    Dashboard, Enabled, Disabled
}

enum class ThemeSegmentKeys {
    Classic, Modern, Generic, Custom
}

enum class IconSegmentKeys {
    Settings, Delete, Done, Person
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var switchState by remember { mutableStateOf(false) }

            val dashboardTab = CarTabLayout.Tab(
                title = "Dashboard",
                icon = painterResource(R.drawable.ic_grid_48),
                iconActive = painterResource(R.drawable.ic_grid_filled_48),
                key = MainTabKeys.Dashboard
            )

            val enabledTab = CarTabLayout.Tab(
                title = "Enabled Tab",
                icon = painterResource(R.drawable.ic_arrow_outwards_48),
                key = MainTabKeys.Enabled
            )

            val disabledTab = CarTabLayout.Tab(
                title = "DisabledTab",
                icon = painterResource(R.drawable.ic_close_48),
                enabled = switchState,
                key = MainTabKeys.Disabled
            )

            var selectedTabKey by remember { mutableStateOf(MainTabKeys.Dashboard) }
            var selectedThemeSegmentKey by remember { mutableStateOf<ThemeSegmentKeys?>(ThemeSegmentKeys.Modern) }
            var selectedIconSegment by remember { mutableStateOf(IconSegmentKeys.Settings)}

            val theme: UiTheme = when (selectedThemeSegmentKey) {
                ThemeSegmentKeys.Modern -> CarComposeTheme.PolestarModern
                ThemeSegmentKeys.Classic -> CarComposeTheme.PolestarClassic
                ThemeSegmentKeys.Custom -> CustomCarTheme
                else -> CarComposeTheme.Generic
            }

            theme.CarTheme {
                CarTabLayout(
                    headerTitle = "Car Compose",
                    tabOrientation = CarTabLayout.Orientation.Vertical,
                    headerStartContent = { HeaderIconDummy() },
                    headerIconButtons = listOf(
                        {
                            var buttonActive by remember { mutableStateOf(false) }
                            CarIconButton(
                                imageVector = Icons.Outlined.Settings,
                                activeImageVector = Icons.Default.Settings,
                                active = buttonActive,
                                onClick = { buttonActive = !buttonActive }
                            )
                        },
                        {
                            var buttonActive by remember { mutableStateOf(false) }
                            CarIconButton(
                                painter = painterResource(R.drawable.ic_grid_48),
                                activePainter = painterResource(R.drawable.ic_grid_filled_48),
                                active = buttonActive,
                                onClick = { buttonActive = !buttonActive }
                            )
                        }
                    ),
                    selectedKey = selectedTabKey,
                    onTabSelected = { key -> selectedTabKey = key },
                    tabs = listOf(
                        dashboardTab,
                        enabledTab,
                        disabledTab,
                    )
                ) { key ->
                    CarColumn {
                        CarRow(
                            title = "Hello World!",
                            description = "This is a row element with content text!"
                        )
                        CarListDivider()
                        CarRow(
                            title = "${key.javaClass.name}",
                            description = "Selected Tab Key"
                        )
                        CarListDivider()
                        CompositionLocalProvider(
                            LocalContentColor provides CarTheme.carColors.accent
                        ) {
                            CarRow(
                                title = "Row with trailing Content",
                                trailingContent = {
                                    HeaderIconDummy()
                                }
                            )
                        }
                        CarListDivider()
                        CarRow(
                            title = "Car Theme",
                            descriptionContent = {

                                val themeSegments = listOf(
                                    CarSegmentedButton.Segment(
                                        content = { Text("PS Modern") },
                                        key = ThemeSegmentKeys.Modern,
                                    ),
                                    CarSegmentedButton.Segment(
                                        content = { Text("PS Classic") },
                                        key = ThemeSegmentKeys.Classic,
                                    ),
                                    CarSegmentedButton.Segment(
                                        content = { Text("Generic") },
                                        key = ThemeSegmentKeys.Generic,
                                    ),
                                    CarSegmentedButton.Segment(
                                        content = { Text("Custom") },
                                        key = ThemeSegmentKeys.Custom,
                                    ),
                                )

                                CarSegmentedButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    segments = themeSegments,
                                    canDeselect = true,
                                    selectedKey = selectedThemeSegmentKey,
                                    onSegmentChanged = { selectedThemeSegmentKey = it },
                                )
                            }
                        )
                        CarListDivider()
                        CarRow(
                            title = "Segmented Icon Buttons",
                            descriptionContent = {
                                Row() {
                                    @Composable
                                    fun SegmentButtonIcon(
                                        imageVector: ImageVector
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .height(CarTheme.carDimensions.iconButtonSize)
                                                .width(80.dp),
                                            imageVector = imageVector,
                                            contentDescription = null
                                        )
                                    }
                                    val iconSegments = listOf(
                                        CarSegmentedButton.Segment(
                                            content = { SegmentButtonIcon(Icons.Default.Settings) },
                                            key = IconSegmentKeys.Settings
                                        ),
                                        CarSegmentedButton.Segment(
                                            content = { SegmentButtonIcon(Icons.Default.Done) },
                                            key = IconSegmentKeys.Done
                                        ),
                                        CarSegmentedButton.Segment(
                                            content = { SegmentButtonIcon(Icons.Default.Person) },
                                            key = IconSegmentKeys.Person
                                        ),
                                        CarSegmentedButton.Segment(
                                            content = { SegmentButtonIcon(Icons.Default.Delete) },
                                            key = IconSegmentKeys.Delete,
                                            enabled = switchState
                                        ),
                                    )

                                    CarSegmentedButton(
                                        modifier = Modifier
                                            .width(IntrinsicSize.Min)
                                            .height(80.dp),
                                        dimensions = CarButtonDefaults.dimensions.copy(
                                            minWidth = 0.dp,
                                            horizontalPadding = 0.dp,
                                            verticalPadding = 10.dp
                                        ),
                                        segments = iconSegments ,
                                        selectedKey = selectedIconSegment,
                                        onSegmentChanged = { it?.let { selectedIconSegment = it }}
                                    )
                                    Box(Modifier.weight(1f))
                                }

                            }
                        )
                        CarListDivider()
                        CarRowSwitch(
                            title = "Enable disabled Tab",
                            state = switchState
                        ) { switchState = it }
                        CarListDivider()
                        CarRow(
                            title = "Row with everything",
                            description = "Description for everything\nOver Multiple\nLines",
                            browsable = true,
                            leadingContent =  {
                                Icon(
                                    painter = CarIcons.settings,
                                    contentDescription = null
                                )
                            },
                            trailingContent = {
                                HeaderIconDummy()
                            }
                        )
                        CarListDivider()
                        CarRow(
                            title = "Text Box?",
                            descriptionContent = {
                                var textValue by remember { mutableStateOf("") }
                                CarTextField(
                                    value = textValue,
                                    onValueChange = { textValue = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    leadingIcon = { Icon(
                                        Icons.Default.Search,
                                        null,
                                        modifier = Modifier.size(40.dp)
                                    )},
                                    placeholder = "Text field demo"
                                )
                                Spacer(Modifier.size(CarTheme.carDimensions.defaultVerticalPadding))
                                CarTextField(
                                    value = textValue,
                                    enabled = false,
                                    onValueChange = { textValue = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    trailingIcon = {
                                        Icon(
                                            imageVector = if (switchState) Icons.Default.Check else Icons.Default.Close,
                                            tint = if (switchState) Color.Green else Color.Red,
                                            contentDescription = null,
                                            modifier = Modifier.size(40.dp)
                                        )
                                    },
                                    placeholder = "Disabled text field"
                                )
                                Spacer(Modifier.size(CarTheme.carDimensions.defaultVerticalPadding))
                                CarTextField(
                                    value = textValue,
                                    onValueChange = { textValue = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = "5 Line text field",
                                    minLines = 5
                                )
                                Spacer(Modifier.size(CarTheme.carDimensions.defaultVerticalPadding))
                                CarTextField(
                                    value = textValue,
                                    onValueChange = { textValue = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = "Read only single line",
                                    readOnly = true,
                                    singleLine = true
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}