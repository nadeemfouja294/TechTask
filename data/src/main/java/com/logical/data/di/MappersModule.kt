package com.logical.data.di

import com.logical.data.mappers.ImageDetailsApiToDatabaseMapper
import com.logical.data.mappers.ImageDetailsApiToDomainMapper
import com.logical.data.mappers.ImageDetailsDatabaseToDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is a Hilt module used to provide dependencies for the mappers.
 * It is installed in the [SingletonComponent] which means the provided dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class MappersModule {
    /**
     * This function provides an instance of [ImageDetailsApiToDomainMapper].
     * The provided [ImageDetailsApiToDomainMapper] will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideImageDetailsApiToDomainMapper(): ImageDetailsApiToDomainMapper =
        ImageDetailsApiToDomainMapper()

    /**
     * This function provides an instance of [ImageDetailsDatabaseToDomainMapper].
     * The provided [ImageDetailsDatabaseToDomainMapper] will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideImageDetailsDatabaseToDomainMapper(): ImageDetailsDatabaseToDomainMapper =
        ImageDetailsDatabaseToDomainMapper()

    /**
     * This function provides an instance of [ImageDetailsApiToDatabaseMapper].
     * The provided [ImageDetailsApiToDatabaseMapper] will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideImageDetailsApiToDatabaseMapper(): ImageDetailsApiToDatabaseMapper =
        ImageDetailsApiToDatabaseMapper()
}