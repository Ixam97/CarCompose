package de.ixam97.carcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.R
import de.ixam97.carcompose.theme.themes.parisDaisy

@Composable
fun HeaderIconDummy(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(48.dp)
            .width(48.dp)
            .background(parisDaisy),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            tint = Color.Black
        )
    }
}