package com.logical.techtask.model

/**
 * This is a data class that represents the model for image details in the presentation layer.
 * It contains all the necessary information about an image that will be presented to the user.
 */
data class ImageDetailsPresentationModel(
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