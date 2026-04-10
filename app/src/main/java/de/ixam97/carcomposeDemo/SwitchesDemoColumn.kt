package de.ixam97.carcomposeDemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.ixam97.carcompose.components.controls.CarRowSwitch
import de.ixam97.carcompose.components.layout.CarColumn
import de.ixam97.carcompose.components.layout.CarListItem
import de.ixam97.carcompose.components.layout.CarListSection

@Composable
fun SwitchesDemoColumn() {
    var state by remember { mutableStateOf(false) }
    CarColumn() {
        CarListSection(
            listItems = listOf(
                CarListItem {
                    CarRowSwitch(
                        title = "Demo Switch",
                        state = state
                    ) { state = it }
                },
                CarListItem {
                    CarRowSwitch(
                        title = "Demo Switch",
                        enabled = false,
                        state = !state,
                    ) { state = !it }
                },
            )
        )
    }
}