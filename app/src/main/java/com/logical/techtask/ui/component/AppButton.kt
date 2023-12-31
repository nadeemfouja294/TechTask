package com.logical.techtask.ui.component

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Composable function to display a customized app button.
 *
 * @param modifier Modifier to be applied to the button composable.
 * @param text The text to be displayed on the button.
 * @param textColor The color of the button text.
 * @param backgroundColor The background color of the button.
 * @param onClick The callback function to be executed when the button is clicked.
 */
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colors.onBackground,
    backgroundColor: Color = MaterialTheme.colors.background,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
    ) {
        Text(text = text, color = textColor)
    }
}