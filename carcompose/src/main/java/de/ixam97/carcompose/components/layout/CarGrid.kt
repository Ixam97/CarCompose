package de.ixam97.carcompose.components.layout

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.components.layout.CarGrid.Tile
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.buildGradientBrush

internal val LocalNumColumns = compositionLocalOf { 3 }

object CarGrid {
    data class Tile(
        val title: String,
        val description: String? = null,
        @DrawableRes val drawableRes: Int? = null,
        val onClick: () -> Unit = {},
        val content: @Composable (() -> Unit)? = null
    )
}

data class CarGridItem(
    val content: @Composable () -> Unit
)

/**
 * Composable variant of a Grid Section layout.
 */
@Composable
fun CarGridSection(
    sectionTitle: String? = null,
    gridItems: List<Tile>,
    numColumns: Int? = null
) {
    BoxWithConstraints {
        val constraints = this
        val numColumns = numColumns ?: if (constraints.maxWidth > 1100.dp) 4 else 3

        CompositionLocalProvider(LocalNumColumns provides numColumns) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                sectionTitle?.let {
                    CarListSectionTitle(it)
                }
                CarGridTileLayout(
                    modifier = Modifier
                        .padding(horizontal = CarTheme.carDimensions.defaultHorizontalPadding),
                    tiles = gridItems
                )
            }
        }
    }
}

/**
 * Car Grid Section layout intended for use in lazy lists.
 */
fun LazyListScope.carGridSection(
    sectionTitle: String? = null,
    gridItems: List<Tile>,
    numColumns: Int? = null
) {
    sectionTitle?.let {
        item { CarListSectionTitle(it) }
    }
    item {
        BoxWithConstraints {
            val constraints = this
            val numColumns = numColumns ?: if (constraints.maxWidth > 1100.dp) 4 else 3

            CompositionLocalProvider(LocalNumColumns provides numColumns) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CarGridTileLayout(
                        modifier = Modifier
                            .padding(horizontal = CarTheme.carDimensions.defaultHorizontalPadding),
                        tiles = gridItems
                    )
                }
            }
        }
    }
}

@Composable
private fun CarGridTileLayout(
    modifier: Modifier = Modifier,
    tiles: List<Tile>,
    numColumns: Int = LocalNumColumns.current
) {
    val numRows = tiles.size / numColumns + if (tiles.size % numColumns > 0) 1 else 0

    Column(
        modifier = modifier
            .padding(vertical = CarTheme.carDimensions.mediumVerticalPadding),
        verticalArrangement = Arrangement.spacedBy(CarTheme.carDimensions.defaultHorizontalPadding)
    ) {
        for (row in 0..<numRows) {
            val rowStartIndex = row * numColumns
            val rowEndIndex = (rowStartIndex + numColumns).coerceAtMost(tiles.size)
            val numRowTiles = rowEndIndex - rowStartIndex
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(CarTheme.carDimensions.defaultHorizontalPadding)
            ) {
                tiles.subList(rowStartIndex, rowEndIndex).forEach {
                    CarGridTile(
                        title = it.title,
                        description = it.description,
                        content = it.content,
                        onClick = it.onClick
                    )
                }
                if (numRowTiles - numColumns < 0) {
                    for (dummy in 0..<(numColumns - numRowTiles)) {
                        GridPaneDummy()
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.CarGridTile(
    title: String,
    description: String? = null,
    onClick: () -> Unit = {},
    content: @Composable (() -> Unit)? = null
) {
    Column(
        modifier =  Modifier
            .clickable(
                enabled = true,
                onClick = onClick
            )
            .fillMaxHeight()
            .weight(1f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            if (content != null) content()
            else {
                Box(Modifier
                    .fillMaxSize()
                    .background(buildGradientBrush(CarTheme.carColors.secondarySurface))
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(top = CarTheme.carDimensions.defaultVerticalPadding),
            text = title,
            style = CarTheme.carTypography.rowTitle,
            maxLines = 1,
            softWrap = false
        )
        description?.let {
            Text(
                modifier = Modifier
                    .padding(top = CarTheme.carDimensions.rowTextSpacing / 2),
                text = it,
                style = CarTheme.carTypography.gridTileDescrription,
                color = CarTheme.carColors.onSurface.copy(alpha = 0.7f),
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

@Composable
private fun RowScope.GridPaneDummy() {
    Box(Modifier.weight(1f))
}