package com.logical.techtask.di

import android.content.Context
import com.logical.techtask.mapper.ImageDetailsPresentationToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is a Hilt module used to provide dependencies for the UI layer.
 * Due to [SingletonComponent], the provided dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    /**
     * This function provides an instance of [ImageDetailsPresentationToUiMapper].
     * The provided instance will have a singleton scope.
     */
    @Provides
    @Singleton
    fun providesImageDetailsPresentationToUiMapper(): ImageDetailsPresentationToUiMapper =
        ImageDetailsPresentationToUiMapper()

    /**
     * This function provides an instance of [Resources] from the application context.
     * It uses the [ApplicationContext] qualifier to tell Hilt to inject the application context.
     */
    @Provides
    fun providesResources(@ApplicationContext context: Context) = context.resources
}