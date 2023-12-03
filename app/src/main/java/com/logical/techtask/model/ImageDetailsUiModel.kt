package com.logical.techtask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * This is a data class that represents the model for image details in the UI layer.
 * It contains all the necessary information about an image that will be displayed to the user.
 * It implements [Parcelable] so it can be passed between components as needed.
 */
@Parcelize
data class ImageDetailsUiModel(
    val id: Long,
    val pageURL: String,
    val type: String,
    val tags: List<String>,
    val previewURL: String,
    val webFormatURL: String,
    val largeImageURL: String,
    val downloads: String,
    val likes: String,
    val comments: String,
    val userId: Long,
    val user: String,
    val userImageURL: String
) : Parcelable