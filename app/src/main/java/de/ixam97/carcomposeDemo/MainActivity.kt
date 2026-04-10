package de.ixam97.carcomposeDemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcomposeDemo.navigationDecorators.LocalSharedViewModelStoreOwner
import de.ixam97.carcomposeDemo.navigationDecorators.SharedViewModelStoreNavEntryDecorator
import de.ixam97.carcomposeDemo.navigationDecorators.rememberSharedViewModelStoreNavEntryDecorator

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val mainViewModel: MainViewModel = viewModel()
            val backStack = rememberNavBackStack(MainScreenNavKey)

            val themeState = mainViewModel.themeState.collectAsState()

            val darkMode = when (themeState.value.themeBrightnessMode) {
                ThemeBrightnessMode.Dark -> true
                ThemeBrightnessMode.Bright -> false
                ThemeBrightnessMode.Auto -> isSystemInDarkTheme()
            }

            CarTheme(
                carThemeConfig = themeState.value.carThemeConfig,
                darkTheme = darkMode
            ) {

                NavDisplay(
                    backStack = backStack,
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator { true },
                        rememberSharedViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = entryProvider {
                        entry<MainScreenNavKey> {
                            MainScreen(
                                mainViewModel = mainViewModel,
                                backStack = backStack
                            )
                        }
                        entry<ThemeSelectionScreenNavKey> {
                            ThemeSelectionScreen(
                                mainViewModel = mainViewModel,
                                backStack = backStack
                            )
                        }
                        entry<TabScreenNavKey>(
                            clazzContentKey = { key -> key.toContentKey()}
                        ) {
                            TabScreen(
                                mainViewModel = mainViewModel,
                                backStack = backStack
                            )
                        }
                        entry<TabScreenSettingsScreenNavKey>(
                            metadata = SharedViewModelStoreNavEntryDecorator.parent(TabScreenNavKey.toContentKey())
                        ) {
                            val parentViewModel = viewModel(
                                modelClass = TabScreenViewModel::class,
                                viewModelStoreOwner = LocalSharedViewModelStoreOwner.current
                            )
                            TabScreenSettingsScreen(
                                viewModel = parentViewModel,
                                backStack = backStack
                            )
                        }
                    },
                    transitionSpec = {
                        // Slide in from right when navigating forward
                        slideInHorizontally(initialOffsetX = { it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { -it })
                    },
                    popTransitionSpec = {
                        // Slide in from left when navigating back
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it })
                    },
                    predictivePopTransitionSpec = {
                        // Slide in from left when navigating back
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it })
                    },
                )
            }
        }
    }
}

fun NavKey.toContentKey() = this.toString()

@Composable
fun adaptiveIconPainterResource(
    @DrawableRes resID: Int
): Painter {
    val context = LocalContext.current
    val adaptiveIcon = remember(resID) {
        ResourcesCompat.getDrawable(context.resources, resID, context.theme)
    }
    return if (adaptiveIcon != null) {
        remember(resID) { BitmapPainter(adaptiveIcon.toBitmap().asImageBitmap()) }
    } else {
        rememberVectorPainter(Icons.Default.Warning)
    }
}