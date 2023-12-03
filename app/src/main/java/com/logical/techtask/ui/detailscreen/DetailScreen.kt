package com.logical.techtask.ui.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.logical.techtask.R
import com.logical.techtask.model.ImageDetailsUiModel
import com.logical.techtask.ui.component.ImageView
import com.logical.techtask.ui.mainlistscreen.TagRow
import com.logical.techtask.utils.TestTags.DETAIL_SCREEN_COMMENT
import com.logical.techtask.utils.TestTags.DETAIL_SCREEN_DOWNLOAD
import com.logical.techtask.utils.TestTags.DETAIL_SCREEN_LARGE_IMAGE
import com.logical.techtask.utils.TestTags.DETAIL_SCREEN_LIKE
import com.logical.techtask.utils.TestTags.DETAIL_SCREEN_SOURCE_CREDIT
import com.logical.techtask.utils.TestTags.IMAGE_DETAIL
import com.logical.techtask.utils.TestTags.USERNAME
import com.logical.techtask.utils.openBrowser


val DrawableId = SemanticsPropertyKey<Int>("DrawableResId")
var SemanticsPropertyReceiver.drawableId by DrawableId

/**
 * Composable function to display the detail screen of an image.
 *
 * @param imageDetail The [ImageDetailsUiModel] data to be displayed on the detail screen.
 * @param navController The navigation controller used for navigating back from the detail screen.
 */
@ExperimentalLayoutApi
@Composable
fun DetailScreen(
    imageDetail: ImageDetailsUiModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.testTag(USERNAME),
                        text = imageDetail.user
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary

            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .testTag(IMAGE_DETAIL)
        ) {
            item {
                LargeImageCard(imageDetail.largeImageURL)

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
                    mainAxisAlignment = FlowMainAxisAlignment.Center,
                    mainAxisSpacing = 10.dp,
                    crossAxisSpacing = 10.dp
                ) {
                    IconTextStack(
                        modifier = Modifier.testTag(DETAIL_SCREEN_DOWNLOAD),
                        icon = R.drawable.ic_download_24,
                        imageDetail.downloads
                    )
                    IconTextStack(
                        modifier = Modifier.testTag(DETAIL_SCREEN_COMMENT),
                        icon = R.drawable.ic_comment_24,
                        imageDetail.comments
                    )
                    IconTextStack(
                        modifier = Modifier.testTag(DETAIL_SCREEN_LIKE),
                        icon = R.drawable.ic_like_24,
                        imageDetail.likes
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                TagRow(
                    modifier = Modifier.fillMaxWidth(),
                    tags = imageDetail.tags
                )
                Spacer(modifier = Modifier.height(20.dp))
                SourceCreditView(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

/**
 * Composable function to display an icon with text stacked below it.
 *
 * @param modifier The [Modifier] to apply to the composable.
 * @param icon The resource ID of the icon to display.
 * @param text The text to display below the icon.
 */
@Composable
fun IconTextStack(
    modifier: Modifier,
    icon: Int,
    text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier
                .size(54.dp)
                .padding(12.dp)
                .semantics { drawableId = icon },
            tint = MaterialTheme.colors.onBackground
        )
        Text(
            text = text,
            fontSize = 18.sp,
            style = MaterialTheme.typography.overline,
            color = MaterialTheme.colors.onBackground
        )
    }
}

/**
 * Composable function to display a card with a large image.
 *
 * @param imagePath The URL of the image to be displayed.
 */
@Composable
private fun LargeImageCard(imagePath: String) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
            .testTag(DETAIL_SCREEN_LARGE_IMAGE),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 6.dp
    ) {
        ImageView(context, imagePath, false, Modifier)
    }
}

/**
 * Composable function to display the source credit for the image.
 *
 * @param modifier The [Modifier] to apply to the composable.
 */
@Composable
private fun SourceCreditView(modifier: Modifier) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.source))
        pushStringAnnotation(
            tag = stringResource(R.string.source),
            annotation = stringResource(R.string.source_url)
        )
        withStyle(style = SpanStyle(color = MaterialTheme.colors.onBackground)) {
            append(stringResource(R.string.source_url))
        }
    }
    ClickableText(
        modifier = modifier.testTag(DETAIL_SCREEN_SOURCE_CREDIT),
        text = annotatedString,
        style = TextStyle(
            color = MaterialTheme.colors.onBackground
        ),
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = context.getString(R.string.source),
                start = offset,
                end = offset
            ).firstOrNull()?.let { url ->
                context.openBrowser(url.item)
            }
        }

    )
}