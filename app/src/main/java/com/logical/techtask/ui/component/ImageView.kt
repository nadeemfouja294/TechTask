package com.logical.techtask.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

/**
 * Composable function to display an image using Coil image loading library.
 *
 * @param context The context of the calling activity or fragment.
 * @param imagePath The URL or file path of the image to be loaded.
 * @param isCrossFadeEnable If true, enables crossfade animation while loading the image.
 * @param modifier Modifier to be applied to the image composable.
 */
@Composable
fun ImageView(
    context: Context,
    imagePath: String,
    isCrossFadeEnable: Boolean,
    modifier: Modifier
) {
    val imageRequest = ImageRequest.Builder(context)
        .data(imagePath)
        .size(coil.size.Size.ORIGINAL)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)

    if (isCrossFadeEnable) {
        imageRequest.crossfade(isCrossFadeEnable)
        imageRequest.transformations(CircleCropTransformation())
    }

    val painter = rememberAsyncImagePainter(imageRequest.build(), context.imageLoader)

    if (painter.state is AsyncImagePainter.State.Loading) {
        CircularProgressIndicator(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colors.onBackground
        )
    } else {
        Image(
            painter = painter,
            contentDescription = imagePath,
            modifier = modifier,
            contentScale = ContentScale.FillBounds
        )
    }
}