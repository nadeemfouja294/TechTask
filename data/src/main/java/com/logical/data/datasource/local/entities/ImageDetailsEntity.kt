package com.logical.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This is a Room entity class that represents the "image_details" table in the local database.
 * Each instance of this class represents a row in the table.
 */
@Entity(tableName = "image_details")
data class ImageDetailsEntity(
    @PrimaryKey
    val id: Long,
    val searchQuery: String,
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
    val userImageURL: String,
    val createdAt: Long
)