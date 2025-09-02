package de.ixam97.carcompose.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.ixam97.carcompose.theme.CarTheme
import de.ixam97.carcompose.utils.buildGradientBrush

data class CarListItem(
    val content: @Composable () -> Unit
)

/**
 * Default list divider using the CarTheme
 */
@Composable
fun CarListDivider() {
    Box(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .padding(horizontal = CarTheme.carDimensions.defaultHorizontalPadding)
            .background(
                brush = buildGradientBrush(CarTheme.carColors.secondaryDivider)
            )
    )
}

/**
 * Variant of the Car List Divider to be used in a Lazy List.
 */
fun LazyListScope.carListDivider() {
    item { CarListDivider() }
}

/**
 * The Title Text for a section within a list view.
 */
@Composable
fun CarListSectionTitle(
    title: String
) {
    Text(
        modifier = Modifier
            .padding(horizontal = CarTheme.carDimensions.defaultHorizontalPadding)
            .padding(
                top = CarTheme.carDimensions.mediumVerticalPadding,
                bottom = CarTheme.carDimensions.defaultVerticalPadding
            ),
        text = title,
        style = CarTheme.carTypography.rowTitle,
        color = CarTheme.carColors.accent
    )
    CarListDivider()
}

/**
 * Car List Section to be used in a regular Column
 */
@Composable
fun ColumnScope.CarListSection(
    sectionTitle: String? = null,
    listItems: List<CarListItem>
) {
    sectionTitle?.let {
        CarListSectionTitle(it)
    }

    listItems.forEachIndexed { index, listItem ->
        listItem.content()
        if (index < listItems.size - 1) {
            CarListDivider()
        }
    }
}

/**
 * Car List Section to be used in a Lazy List
 */
fun LazyListScope.carListSection(
    sectionTitle: String? = null,
    listItems: List<CarListItem>
) {
    sectionTitle?.let {
        item { CarListSectionTitle(it) }
    }
    listItems.forEachIndexed { index, listItem ->
        item {
            listItem.content()
            if (index < listItems.size - 1) {
                CarListDivider()
            }
        }
    }
}