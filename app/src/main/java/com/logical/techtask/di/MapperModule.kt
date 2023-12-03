package com.logical.techtask.di

import com.logical.techtask.mapper.ImageDetailsDomainToPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is a Hilt module used to provide dependencies for the mappers.
 * Due to [SingletonComponent], the provided dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    /**
     * This function provides an instance of [ImageDetailsDomainToPresentationMapper].
     * The provided instance will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideImageDetailsDomainToPresentationMapper(): ImageDetailsDomainToPresentationMapper =
        ImageDetailsDomainToPresentationMapper()

}