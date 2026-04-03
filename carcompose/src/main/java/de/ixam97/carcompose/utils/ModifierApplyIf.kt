package de.ixam97.carcompose.utils

import androidx.compose.ui.Modifier

inline fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

inline fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
    elseModifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        then(elseModifier(Modifier))
    }
}