package com.logical.data.mappers

import com.logical.data.common.ApiToDatabaseMapper
import com.logical.data.common.orZero
import com.logical.data.datasource.local.entities.ImageDetailsEntity
import com.logical.data.models.ImageDetailsApiModel

/**
 * This is a mapper class that converts [ImageDetailsApiModel] to [ImageDetailsEntity].
 * It extends [ApiToDatabaseMapper] which is a generic abstract class for mapping API models to database entities.
 * This class is used to transform the data fetched from the API into a format that can be stored in the local database.
 */

class ImageDetailsApiToDatabaseMapper : ApiToDatabaseMapper<MapperInput, ImageDetailsEntity>() {
    override fun map(input: MapperInput): ImageDetailsEntity {
        val imageDetail = input.apiImageDetails
        return ImageDetailsEntity(
            id = imageDetail.id.orZero(),
            pageURL = imageDetail.pageURL.orEmpty(),
            type = imageDetail.type.orEmpty(),
            tags = imageDetail.tags.orEmpty(),
            previewURL = imageDetail.previewURL.orEmpty(),
            webFormatURL = imageDetail.webFormatURL.orEmpty(),
            largeImageURL = imageDetail.largeImageURL.orEmpty(),
            downloads = imageDetail.downloads.orZero(),
            likes = imageDetail.likes.orZero(),
            comments = imageDetail.comments.orZero(),
            userId = imageDetail.userId.orZero(),
            user = imageDetail.user.orEmpty(),
            userImageURL = imageDetail.userImageURL.orEmpty(),
            searchQuery = input.searchQuery,
            createdAt = input.createdAt
        )
    }
}

/**
 * This is a data class representing the input for the [ImageDetailsApiToDatabaseMapper].
 *
 * @property apiImageDetails The [ImageDetailsApiModel] to be mapped.
 * @property searchQuery The search query used to fetch the image details.
 * @property createdAt The creation time of the image details.
 */
data class MapperInput(
    val apiImageDetails: ImageDetailsApiModel,
    val searchQuery: String,
    val createdAt: Long
)