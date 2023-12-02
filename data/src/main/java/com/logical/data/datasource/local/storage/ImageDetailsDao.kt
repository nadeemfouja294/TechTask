package com.logical.data.datasource.local.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.logical.data.datasource.local.entities.ImageDetailsEntity

/**
 * This is a Room DAO interface that provides access to the "image_details" table in the local database.
 * It includes methods for inserting, querying, and deleting data from the table.
 */
@Dao
interface ImageDetailsDao {
    /**
     * This function inserts a list of [ImageDetailsEntity] into the "image_details" table.
     * If a conflict occurs, the existing row will be replaced with the new data.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<ImageDetailsEntity>)

    /**
     * This function retrieves all [ImageDetailsEntity] from the "image_details" table that match the provided query.
     */
    @Query("SELECT * FROM image_details WHERE searchQuery=:query")
    suspend fun getAllImageDetails(query: String): List<ImageDetailsEntity>?

    /**
     * This function deletes all [ImageDetailsEntity] from the "image_details" table that match the provided query.
     */
    @Query("DELETE  FROM image_details WHERE searchQuery=:query")
    suspend fun deleteBySearchQuery(query: String)
}