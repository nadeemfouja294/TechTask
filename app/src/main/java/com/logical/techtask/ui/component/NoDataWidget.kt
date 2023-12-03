package com.logical.techtask.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.logical.techtask.R
import com.logical.techtask.utils.TestTags.EMPTY_IMAGE_LIST

/**
 * Composable function to display a "No Data" widget when there is no data available.
 * It shows a title "Oops" in bold and a message "No data found".
 */

@Composable
fun NoDataWidget() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize()
            .testTag(EMPTY_IMAGE_LIST)
    ) {
        Text(
            text = stringResource(R.string.oops),
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.primary
        )
        Text(
            text = stringResource(R.string.no_data_found),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.primary
        )
    }
}