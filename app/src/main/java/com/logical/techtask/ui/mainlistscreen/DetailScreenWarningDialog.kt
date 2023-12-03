package com.logical.techtask.ui.mainlistscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.logical.techtask.R
import com.logical.techtask.model.ImageDetailsUiModel
import com.logical.techtask.ui.component.AppButton
import com.logical.techtask.ui.component.ImageView

/**
 * Composable function to display a custom warning dialog.
 *
 * @param openDialog The [MutableState] that controls the visibility of the dialog.
 * @param imageDetail The [ImageDetailsUiModel] data to be displayed in the dialog.
 * @param onPositive The callback function to be executed when the positive button is clicked.
 * @param onNegative The callback function to be executed when the negative button is clicked.
 */
@Composable
fun DetailScreenWarningDialog(
    openDialog: MutableState<Boolean>,
    imageDetail: ImageDetailsUiModel,
    onPositive: () -> Unit,
    onNegative: () -> Unit
) {
    Dialog(properties = DialogProperties(), onDismissRequest = { openDialog.value = false }) {
        DetailScreenWarningDialogUI(imageDetail, onPositive, onNegative)
    }
}

/**
 * Composable function to display the UI of the custom warning dialog.
 *
 * @param imageDetail The [ImageDetailsUiModel] data to be displayed in the dialog.
 * @param onPositive The callback function to be executed when the positive button is clicked.
 * @param onNegative The callback function to be executed when the negative button is clicked.
 */
@Composable
fun DetailScreenWarningDialogUI(
    imageDetail: ImageDetailsUiModel,
    onPositive: () -> Unit,
    onNegative: () -> Unit

) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()

        ) {
            Spacer(modifier = Modifier.height(12.dp))

            ImageView(context, imageDetail.previewURL, true, Modifier.size(60.dp))

            Text(
                text = context.getString(R.string.detail_screen_warning_dialog, imageDetail.user),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                AppButton(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(id = R.string.detail_screen_warning_dialog_negative_text),
                    textColor = MaterialTheme.colors.error,
                    onClick = { onNegative.invoke() }

                )
                Spacer(modifier = Modifier.height(8.dp))
                AppButton(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(id = R.string.detail_screen_warning_dialog_positive_text),
                    onClick = { onPositive.invoke() }

                )
            }
        }
    }
}