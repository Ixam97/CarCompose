package de.ixam97.carcomposeDemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import de.ixam97.carcompose.components.controls.CarIconButton
import de.ixam97.carcompose.components.layout.CarTabLayout
import kotlinx.serialization.Serializable

@Serializable
data object TabScreenNavKey: NavKey

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun TabScreen(
    mainViewModel: MainViewModel,
    backStack: NavBackStack<NavKey>
) {

    val viewModel: TabScreenViewModel = viewModel()
    val state = viewModel.state.collectAsState()

    val tabs = listOf(
        CarTabLayout.Tab(
            title = "Tab 1",
            icon = painterResource(de.ixam97.carcompose.R.drawable.ic_grid_48),
            iconActive = painterResource(de.ixam97.carcompose.R.drawable.ic_grid_filled_48),
            key = TabScreenTabs.Tab1
        ),
        CarTabLayout.Tab(
            title = "Tab 2",
            icon = rememberVectorPainter(Icons.Outlined.AccountCircle),
            iconActive = rememberVectorPainter(Icons.Filled.AccountCircle),
            key = TabScreenTabs.Tab2
        ),
        CarTabLayout.Tab(
            title = "Tab 3",
            icon = rememberVectorPainter(Icons.Outlined.Info),
            iconActive = rememberVectorPainter(Icons.Filled.Info),
            key = TabScreenTabs.Tab3
        )
    )

    CarTabLayout(
        headerTitle = stringResource(R.string.app_name),
        tabOrientation = state.value.layoutType.orientation(),
        onBackAction = { backStack.removeLastOrNull() },
        headerIconButtons = listOf(
            {
                CarIconButton(
                    painter = painterResource(de.ixam97.carcompose.R.drawable.ic_settings_48),
                    onClick = {
                        backStack.add(TabScreenSettingsScreenNavKey)
                    }
                )
            }
        ),
        selectedKey = state.value.selectedTab,
        onTabSelected = { key -> viewModel.setSelectedTab(key) },
        tabs = tabs
    ) { key ->
        SwitchesDemoColumn()
    }
}

/*
@Composable
fun OldStuff() {
    val context = LocalContext.current
    val carSnackBarState = LocalCarSnackBarState.current

    var selected by remember { mutableStateOf(false) }
    CarColumn {
        CarListSection(
            sectionTitle = "Basic Info:",
            dividerAtBottom = true,
            listItems = listOf(
                CarListItem {
                    CarRow(
                        title = "Width: ${windowDpSize.width}, Height: ${windowDpSize.height}",
                        description = "Window size"
                    )
                },
                CarListItem {
                    CarRowRadioButton(
                        title = "Radio Button 1",
                        description = "Description Text of Radio Button 1",
                        isSelected = selected,
                        onSelect = { selected = true }
                    )
                },
                CarListItem {
                    CarRowRadioButton(
                        title = "Radio Button 2",
                        description = "With also leading content!",
                        leadingContent = {
                            Icon(
                                modifier = Modifier.size(CarTheme.carDimensions.iconButtonSize),
                                imageVector = Icons.Default.Info,
                                contentDescription = null
                            )
                        },
                        isSelected = !selected,
                        onSelect = { selected = false }
                    )
                },
                CarListItem {
                    CarRow(
                        title = "${key.javaClass.name}",
                        description = "Selected Tab Key"
                    )
                },
                CarListItem {
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
                }
            )
        )
        CarListSection(
            sectionTitle = "Controls and Theming:",
            dividerAtBottom = true,
            listItems = listOf(
                CarListItem {
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
                                    content = { Text("Volvo Car UX") },
                                    key = ThemeSegmentKeys.Volvo,
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
                    if (selectedThemeSegmentKey == ThemeSegmentKeys.Volvo) {
                        CarRowSwitch(
                            title = "Automatic Bright Mode",
                            state = autoBrightModeSwitchState
                        ) { autoBrightModeSwitchState = it}
                    }
                },
                CarListItem {
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
                },
                CarListItem {
                    CarRowSwitch(
                        title = "Enable disabled Tab",
                        state = switchState
                    ) { switchState = it }
                }
            )
        )
        CarListSection(
            sectionTitle = "Other: ",
            listItems = listOf(
                CarListItem {
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
                        },
                        onBrowse = {
                        }
                    )
                },
                CarListItem {
                    CarRow(
                        content = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(CarTheme.carDimensions.defaultHorizontalPadding)
                            ) {
                                var timesClicked by remember { mutableIntStateOf(0) }
                                val baseSnackConfig = CarSnackBarConfig(
                                    identifier = SnackBar1Identifier,
                                    content = { Text("Times clicked Button: $timesClicked") },
                                    painter = rememberVectorPainter(Icons.Default.Settings),
                                    actionText = "Dismiss",
                                    onAction = { carSnackBarState.cancelSnackBar(SnackBar1Identifier) }
                                )

                                CarButton(
                                    onClick = {
                                        timesClicked++
                                        carSnackBarState.showSnackBar(baseSnackConfig.copy(isError = (timesClicked % 2 == 0)))
                                    }
                                ) { Text("SnackBar 1") }

                                CarButton(
                                    onClick = {
                                        if (carSnackBarState.isSnackBarVisible(SnackBar2Identifier)) {
                                            carSnackBarState.cancelSnackBar(SnackBar2Identifier)
                                        } else {
                                            carSnackBarState.showSnackBar(
                                                CarSnackBarConfig(
                                                    identifier = SnackBar2Identifier,
                                                    content = { Text("Snack Bar 2") },
                                                    duration = null
                                                )
                                            )
                                        }
                                    }
                                ) { Text("SnackBar 2") }
                            }
                        }
                    )
                },
                CarListItem {
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
            )
        )
    }
}
*/