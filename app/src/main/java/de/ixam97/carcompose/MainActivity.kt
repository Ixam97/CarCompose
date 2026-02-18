package de.ixam97.carcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.components.HeaderIconDummy
import de.ixam97.carcompose.components.controls.CarButtonDefaults
import de.ixam97.carcompose.components.controls.CarRow
import de.ixam97.carcompose.components.controls.CarRowSwitch
import de.ixam97.carcompose.components.controls.CarSegmentedButton
import de.ixam97.carcompose.components.layout.CarColumn
import de.ixam97.carcompose.components.layout.CarListDivider
import de.ixam97.carcompose.components.layout.CarTabLayout
import de.ixam97.carcompose.resources.CarIcons
import de.ixam97.carcompose.theme.CarComposeTheme
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.UiTheme
import de.ixam97.carcompose.utils.buildGradientBrush

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var selectedIndex by remember { mutableIntStateOf(0) }
            var switchState by remember { mutableStateOf(false) }

            // CarTheme(
            //     carUiType = when (selectedIndex) {
            //         0 -> UiType.PolestarModern
            //         1 -> UiType.PolestarClassic
            //         else -> UiType.Generic
            //     }
            // ) {

            val theme: UiTheme = when (selectedIndex) {
                0 -> CarComposeTheme.PolestarModern
                1 -> CarComposeTheme.PolestarClassic
                2 -> CarComposeTheme.Generic
                3 -> CustomCarTheme
                else -> CarComposeTheme.Generic
            }

            theme.CarTheme {
                CarTabLayout(
                    headerTitle = "Car Compose",
                    tabOrientation = CarTabLayout.Orientation.HorizontalCompact,
                    headerStartContent = { HeaderIconDummy() },
                    tabSelectedIndex = 0,
                    tabOnIndexChanged = {},
                    tabs = listOf(
                        CarTabLayout.Tab(
                            title = "Selected Tab",
                            icon = CarIcons.settings,
                            enabled = true
                        ),
                        CarTabLayout.Tab(
                            title = "Enabled Tab",
                            icon = CarIcons.arrowOutwards,
                            enabled = true
                        ),
                        CarTabLayout.Tab(
                            title = "Disabled Tab",
                            icon = CarIcons.close,
                            enabled = false
                        )
                    )
                ) {
                    CarColumn {
                        CarRow(
                            title = "Hello World!",
                            description = "This is a row element with content text!"
                        )
                        CarListDivider()
                        CarRow(
                            title = "Row with leading Content",
                            leadingContent = {
                                HeaderIconDummy()
                            }
                        )
                        CarListDivider()
                        CarRow(
                            title = "Row with trailing Content",
                            trailingContent = {
                                HeaderIconDummy()
                            }
                        )
                        CarListDivider()
                        CarRow(
                            title = "Car Theme",
                            descriptionContent = {
                                CarSegmentedButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    buttonContents = listOf(
                                        @Composable {
                                            Text("PS Modern")
                                        },{
                                            Text("PS Classic")
                                        },{
                                            Text("Generic")
                                        },{
                                            Text("Custom")
                                        },
                                    ),
                                    enabledIndexes = listOf(true, true, true, true),
                                    canDeselect = false,
                                    selectedIndex = selectedIndex,
                                    onIndexChanged = { newIndex ->
                                        selectedIndex = newIndex
                                    },
                                )
                            }
                        )
                        CarListDivider()
                        CarRow(
                            title = "Segmented Icon Buttons",
                            descriptionContent = {
                                Row() {
                                    CarSegmentedButton(
                                        modifier = Modifier
                                            .width(IntrinsicSize.Min)
                                            .height(80.dp),
                                        dimensions = CarButtonDefaults.dimensions.copy(
                                            minWidth = 0.dp,
                                            horizontalPadding = 0.dp,
                                            verticalPadding = 10.dp
                                        ),
                                        buttonContents = listOf(
                                            @Composable {
                                                Icon(
                                                    modifier = Modifier
                                                        .height(CarTheme.carDimensions.iconButtonSize)
                                                        .width(80.dp),
                                                    imageVector = Icons.Default.Settings,
                                                    contentDescription = null
                                                )
                                            },{
                                                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                                            },{
                                                Icon(imageVector = Icons.Default.Done, contentDescription = null)
                                            },{
                                                Icon(imageVector = Icons.Default.Person, contentDescription = null)
                                            },
                                        ),
                                        enabledIndexes = listOf(true, true, true, true),
                                        canDeselect = false,
                                        selectedIndex = selectedIndex,
                                        onIndexChanged = { newIndex ->

                                        },
                                    )
                                    Box(Modifier.weight(1f))
                                }

                            }
                        )
                        CarListDivider()
                        CarRowSwitch(
                            title = "Switch Row",
                            state = switchState
                        ) { switchState = !switchState}
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
                            browsable = true,
                            title = "Text Box?",
                            descriptionContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .heightIn(min = 80.dp)
                                        .background(buildGradientBrush(CarTheme.carColors.primarySurface))
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}