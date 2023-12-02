package com.logical.data.di

import com.logical.data.datasource.local.storage.ImageDetailsDao
import com.logical.data.datasource.remote.ApiService
import com.logical.data.mappers.ImageDetailsApiToDatabaseMapper
import com.logical.data.mappers.ImageDetailsApiToDomainMapper
import com.logical.data.mappers.ImageDetailsDatabaseToDomainMapper
import com.logical.data.repo.ImagesRepositoryImp
import com.logical.domain.repositories.ImagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is a Hilt module used to provide dependencies for the repositories.
 * It is installed in the [SingletonComponent] which means the provided dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    /**
     * This function provides an instance of [ImagesRepository].
     * The provided [ImagesRepository] will have a singleton scope.
     * The actual implementation of the [ImagesRepository] is [ImagesRepositoryImp].
     */
    @Singleton
    @Provides
    fun provideImagesRepositoryImp(
        apiService: ApiService,
        imageDetailsDao: ImageDetailsDao,
        imageDetailsApiToDomainMapper: ImageDetailsApiToDomainMapper,
        imageDetailsApiToDatabaseMapper: ImageDetailsApiToDatabaseMapper,
        imageDetailsDatabaseToDomainMapper: ImageDetailsDatabaseToDomainMapper
    ): ImagesRepository {
        return ImagesRepositoryImp(
            apiService,
            imageDetailsDao,
            imageDetailsApiToDomainMapper,
            imageDetailsApiToDatabaseMapper,
            imageDetailsDatabaseToDomainMapper
        )
    }
}