package com.logical.data.models

import com.google.gson.annotations.SerializedName

/**
 * This data class represents the model for image details received from the API.
 * Each variable corresponds to a key in the JSON object from the API response.
 * The @SerializedName annotation is used to specify the key name in the JSON object because it's different from the variable name.
 */
data class ImageDetailsApiModel(
    val id: Long?,
    val pageURL: String?,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val webFormatURL: String?,
    val largeImageURL: String?,
    val downloads: Long?,
    val likes: Long?,
    val comments: Long?,
    @SerializedName("user_id")
    val userId: Long?,
    val user: String?,
    val userImageURL: String?
)