package de.ixam97.carcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import de.ixam97.carcompose.components.HeaderIconDummy
import de.ixam97.carcompose.components.controls.CarRow
import de.ixam97.carcompose.components.layout.CarColumn
import de.ixam97.carcompose.components.layout.CarPaneLayout
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.theme.UiType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarTheme(
                carUiType = UiType.PolestarModern
            ) {
                CarPaneLayout(
                    headerTitle = "Car Compose",
                    headerStartContent = { HeaderIconDummy() }
                ) {
                    CarColumn {
                        CarRow(
                            title = "Hello World!",
                            description = "This is a row element with content text!"
                        )
                    }
                }
            }
        }
    }
}