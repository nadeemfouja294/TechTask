package com.logical.domain.repositories

import com.logical.domain.models.ImageDetailsDomainModel

/**
 * This is an interface for a repository that fetches image details.
 * The implementation of this interface is in the data layer.
 */
interface ImagesRepository {
    suspend fun getImages(query: String): List<ImageDetailsDomainModel>
}