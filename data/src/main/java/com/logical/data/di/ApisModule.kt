package com.logical.data.di

import com.logical.data.datasource.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * This is a Hilt module used to provide dependencies for the API services.
 * It is installed in the [SingletonComponent] which means the provided dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class ApisModule {

    /**
     * This function provides an instance of [ApiService].
     * It takes [Retrofit] as a parameter which will be provided by Hilt.
     * The provided [ApiService] will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}