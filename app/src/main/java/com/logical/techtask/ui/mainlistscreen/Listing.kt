package com.logical.techtask.ui.mainlistscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.logical.techtask.model.ImageDetailsUiModel
import com.logical.techtask.utils.TestTags.IMAGE_LIST

/**
 * Composable for displaying a list of items in a lazy column.
 *
 * @param modifier The modifier for the composable.
 * @param list The list of [ImageDetailsUiModel] items to be displayed in the lazy column.
 * @param onItemClick The callback function to handle item click events.
 */
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalLayoutApi
@Composable
fun Listing(
    modifier: Modifier,
    list: List<ImageDetailsUiModel>,
    onItemClick: (ImageDetailsUiModel) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.testTag(IMAGE_LIST)
    ) {
        items(list.size, { itemKey ->
            itemKey.toString()
        }, itemContent = { itemIndex ->
            ListItem(imageDetailUi = list[itemIndex]) {
                onItemClick(list[itemIndex])
            }
        })
    }
}