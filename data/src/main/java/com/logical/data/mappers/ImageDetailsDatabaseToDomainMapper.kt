package com.logical.data.mappers

import com.logical.data.common.DatabaseToDomainMapper
import com.logical.data.datasource.local.entities.ImageDetailsEntity
import com.logical.domain.models.ImageDetailsDomainModel

/**
 * This is a mapper class that converts [ImageDetailsEntity] to [ImageDetailsDomainModel].
 * It extends [DatabaseToDomainMapper] which is a generic abstract class for mapping database entities to domain models.
 * This class is used to transform the data fetched from the local database into a format that can be used in the domain layer.
 */
class ImageDetailsDatabaseToDomainMapper :
    DatabaseToDomainMapper<ImageDetailsEntity, ImageDetailsDomainModel>() {

    /**
     * This function maps [ImageDetailsEntity] to [ImageDetailsDomainModel].
     *
     * @param input The [ImageDetailsEntity] to be mapped.
     * @return The mapped [ImageDetailsDomainModel].
     */
    override fun map(input: ImageDetailsEntity) = ImageDetailsDomainModel(
        id = input.id,
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