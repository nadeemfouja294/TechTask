package com.logical.data.datasource.local.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.logical.data.datasource.local.entities.ImageDetailsEntity

/**
 * This is a Room database class that provides access to the local database.
 * It includes a [RoomDatabase] that contains the [ImageDetailsEntity] table.
 */
@Database(
    entities = [ImageDetailsEntity::class],
    version = LocalDb.VERSION,
    exportSchema = false
)
abstract class LocalDb : RoomDatabase() {

    /**
     * This function provides an instance of [ImageDetailsDao].
     * The provided [ImageDetailsDao] will allow access to the "image_details" table in the local database.
     */
    abstract fun imageDetailsDao(): ImageDetailsDao

    companion object {
        internal const val VERSION = 1
        private const val NAME = "local_db"

        /**
         * This function creates an instance of [LocalDb].
         * It takes [Context] as a parameter which will be provided by the Android system.
         */
        fun create(applicationContext: Context): LocalDb {
            return Room.databaseBuilder(
                applicationContext,
                LocalDb::class.java,
                NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}