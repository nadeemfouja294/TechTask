package com.logical.domain.models

/**
 * This is a data class representing a list of image details in the domain layer.
 *
 * @property images The list of [ImageDetailsDomainModel] instances.
 */
data class ImagesDomainModel(
    val images: List<ImageDetailsDomainModel>
)