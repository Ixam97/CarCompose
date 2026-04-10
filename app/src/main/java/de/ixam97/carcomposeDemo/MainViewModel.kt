package de.ixam97.carcomposeDemo

import android.os.Build
import androidx.lifecycle.ViewModel
import de.ixam97.carcompose.theme.CarThemeConfig
import de.ixam97.carcompose.theme.autodetectCarThemeConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

val AutoCarThemeConfig : CarThemeConfig = autodetectCarThemeConfig(CustomCarThemeConfig)

data class ThemeState(
    val autoDetectTheme: Boolean = true,
    val carThemeConfig: CarThemeConfig = AutoCarThemeConfig,
    val themeBrightnessMode: ThemeBrightnessMode = when (Build.DEVICE) {
        "clementine_x86_64", "habanero" -> ThemeBrightnessMode.Auto
        else -> ThemeBrightnessMode.Dark
    }
)

enum class ThemeBrightnessMode {
    Dark, Bright, Auto
}

class MainViewModel: ViewModel() {

    private var _themeState = MutableStateFlow(ThemeState())
    val themeState = _themeState.asStateFlow()

    fun setAutoDetectTheme(value: Boolean) {
        if (value) {
            _themeState.update { it.copy(
                carThemeConfig = AutoCarThemeConfig,
                autoDetectTheme = true
            ) }
        } else {
            _themeState.update { it.copy(autoDetectTheme = false) }
        }
    }

    fun setTheme(theme: CarThemeConfig) {
        _themeState.update { it.copy(
            carThemeConfig = theme
        ) }
        if (_themeState.value.carThemeConfig.carBrightColors == null && _themeState.value.themeBrightnessMode == ThemeBrightnessMode.Bright) {
            _themeState.update { it.copy(themeBrightnessMode = ThemeBrightnessMode.Auto) }
        }
    }

    fun setThemeBrightnessMode(themeBrightnessMode: ThemeBrightnessMode) {
        _themeState.update { it.copy(themeBrightnessMode = themeBrightnessMode) }
    }

}