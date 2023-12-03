package com.logical.domain.di

import android.content.res.Resources
import com.logical.domain.repositories.ImagesRepository
import com.logical.domain.usecases.ImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This is a Hilt module used to provide dependencies for the use cases.
 * It is installed in the [SingletonComponent] which means the provided dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    /**
     * This function provides an instance of [ImagesUseCase].
     * It takes [ImagesRepository] and [Resources] as parameters which will be provided by Hilt.
     */
    @Provides
    fun provideImagesUseCase(imagesRepository: ImagesRepository, resources: Resources) =
        ImagesUseCase(imagesRepository, resources)
}