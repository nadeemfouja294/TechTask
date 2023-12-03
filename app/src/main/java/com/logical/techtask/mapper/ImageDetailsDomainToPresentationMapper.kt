package com.logical.techtask.mapper

import com.logical.data.common.DomainToPresentationMapper
import com.logical.data.common.orZero
import com.logical.domain.models.ImageDetailsDomainModel
import com.logical.techtask.model.ImageDetailsPresentationModel

/**
 * This is a mapper class that converts [ImageDetailsDomainModel] to [ImageDetailsPresentationModel].
 * It extends [DomainToPresentationMapper] which is a generic abstract class for mapping domain models to presentation models.
 */
class ImageDetailsDomainToPresentationMapper :
    DomainToPresentationMapper<ImageDetailsDomainModel, ImageDetailsPresentationModel>() {

    /**
     * This function maps [ImageDetailsDomainModel] to [ImageDetailsPresentationModel].
     */
    public override fun map(input: ImageDetailsDomainModel) = ImageDetailsPresentationModel(
        id = input.id.orZero(),
        pageURL = input.pageURL,
        type = input.type,
        tags = input.tags,
        previewURL = input.previewURL,
        webFormatURL = input.webFormatURL,
        largeImageURL = input.largeImageURL,
        downloads = input.downloads,
        likes = input.likes,
        comments = input.comments,
        userId = input.userId,
        user = input.user,
        userImageURL = input.userImageURL
    )
}