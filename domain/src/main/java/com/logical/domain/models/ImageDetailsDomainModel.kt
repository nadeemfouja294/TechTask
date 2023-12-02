package com.logical.domain.models

/**
 * This is a data class representing the details of an image in the domain layer.
 */
data class ImageDetailsDomainModel(
    val id: Long,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val webFormatURL: String,
    val largeImageURL: String,
    val downloads: Long,
    val likes: Long,
    val comments: Long,
    val userId: Long,
    val user: String,
    val userImageURL: String
)