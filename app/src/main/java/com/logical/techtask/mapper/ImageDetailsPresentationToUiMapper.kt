package com.logical.techtask.mapper

import com.logical.data.common.PresentationToUiMapper
import com.logical.data.common.format
import com.logical.data.common.orZero
import com.logical.techtask.model.ImageDetailsPresentationModel
import com.logical.techtask.model.ImageDetailsUiModel

/**
 * This is a mapper class that converts [ImageDetailsPresentationModel] to [ImageDetailsUiModel].
 * It extends [PresentationToUiMapper] which is a generic abstract class for mapping presentation models to UI models.
 */
class ImageDetailsPresentationToUiMapper :
    PresentationToUiMapper<ImageDetailsPresentationModel, ImageDetailsUiModel>() {
    /**
     * This function maps [ImageDetailsPresentationModel] to [ImageDetailsUiModel].
     * It also formats the downloads, likes, and comments for better UI representation.
     */
    override fun map(input: ImageDetailsPresentationModel) = ImageDetailsUiModel(
        id = input.id.orZero(),
        pageURL = input.pageURL,
        type = input.type,
        tags = input.tags.splitTags(),
        previewURL = input.previewURL,
        webFormatURL = input.webFormatURL,
        largeImageURL = input.largeImageURL,
        downloads = input.downloads.format(),
        likes = input.likes.format(),
        comments = input.comments.format(),
        userId = input.userId.orZero(),
        user = input.user,
        userImageURL = input.userImageURL
    )
}

/**
 * This function splits the tags string into a list of tags.
 * If the tags string is empty, it returns an empty list.
 * If the tags string contains a comma, it splits the string by the comma.
 * Otherwise, it returns a list with the tags string as the only element.
 */
private fun String.splitTags(): List<String> {
    return if (isEmpty()) emptyList()
    else if (contains(",")) {
        split(",")
    } else listOf(this)
}