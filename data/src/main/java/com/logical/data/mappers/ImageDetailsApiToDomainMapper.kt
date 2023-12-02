package com.logical.data.mappers

import com.logical.data.common.ApiToDomainMapper
import com.logical.data.common.orZero
import com.logical.data.models.ImageDetailsApiModel
import com.logical.domain.models.ImageDetailsDomainModel

/**
 * This is a mapper class that converts [ImageDetailsApiModel] to [ImageDetailsDomainModel].
 * It extends [ApiToDomainMapper] which is a generic abstract class for mapping API models to domain models.
 * This class is used to transform the data fetched from the API into a format that can be used in the domain layer.
 */
class ImageDetailsApiToDomainMapper :
    ApiToDomainMapper<ImageDetailsApiModel, ImageDetailsDomainModel>() {

    /**
     * This function maps [ImageDetailsApiModel] to [ImageDetailsDomainModel].
     * It uses the extension functions [orZero] and [orEmpty] to provide default values for null properties.
     *
     * @param input The [ImageDetailsApiModel] to be mapped.
     * @return The mapped [ImageDetailsDomainModel].
     */
    override fun map(input: ImageDetailsApiModel) = ImageDetailsDomainModel(
        id = input.id.orZero(),
        pageURL = input.pageURL.orEmpty(),
        type = input.type.orEmpty(),
        tags = input.tags.orEmpty(),
        previewURL = input.previewURL.orEmpty(),
        webFormatURL = input.webFormatURL.orEmpty(),
        largeImageURL = input.largeImageURL.orEmpty(),
        downloads = input.downloads.orZero(),
        likes = input.likes.orZero(),
        comments = input.comments.orZero(),
        userId = input.userId.orZero(),
        user = input.user.orEmpty(),
        userImageURL = input.userImageURL.orEmpty()
    )
}