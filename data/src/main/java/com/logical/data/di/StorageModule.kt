package com.logical.data.di

import android.content.Context
import com.logical.data.datasource.local.storage.ImageDetailsDao
import com.logical.data.datasource.local.storage.LocalDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * This is a Hilt module used to provide dependencies for the local storage.
 * It is installed in the [SingletonComponent] which means the provided dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
    /**
     * This function provides an instance of [LocalDb].
     * It takes [ApplicationContext] as a parameter which will be provided by Hilt.
     */
    @Provides
    fun providesPxDb(@ApplicationContext context: Context) = LocalDb.create(context)

    /**
     * This function provides an instance of [ImageDetailsDao].
     * It takes [LocalDb] as a parameter which will be provided by Hilt.
     */
    @Provides
    fun providesPixabayDao(localDb: LocalDb): ImageDetailsDao = localDb.imageDetailsDao()
}