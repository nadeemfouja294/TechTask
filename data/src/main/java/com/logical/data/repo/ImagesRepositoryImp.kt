package com.logical.data.repo

import com.logical.data.datasource.local.storage.ImageDetailsDao
import com.logical.data.datasource.remote.ApiService
import com.logical.data.mappers.ImageDetailsApiToDatabaseMapper
import com.logical.data.mappers.ImageDetailsApiToDomainMapper
import com.logical.data.mappers.ImageDetailsDatabaseToDomainMapper
import com.logical.data.mappers.MapperInput
import com.logical.domain.models.ImageDetailsDomainModel
import com.logical.domain.repositories.ImagesRepository
import javax.inject.Inject

private const val FETCH_INTERVAL_IN_SECONDS: Int = (24 * 60 * 60)

/**
 * This is a repository class that fetches image data from a remote API or local database.
 * It decides whether to fetch new data from the API or use the existing data in the database based on the last fetch time.
 *
 * @property apiService The service to fetch data from the API.
 * @property imageDetailsDao The DAO to fetch and store data in the local database.
 * @property imageDetailsApiToDomainMapper The mapper to convert data from API model to domain model.
 * @property imageDetailsApiToDatabaseMapper The mapper to convert data from API model to database model.
 * @property imageDetailsDatabaseToDomainMapper The mapper to convert data from database model to domain model.
 */
class ImagesRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val imageDetailsDao: ImageDetailsDao,
    private val imageDetailsApiToDomainMapper: ImageDetailsApiToDomainMapper,
    private val imageDetailsApiToDatabaseMapper: ImageDetailsApiToDatabaseMapper,
    private val imageDetailsDatabaseToDomainMapper: ImageDetailsDatabaseToDomainMapper
) : ImagesRepository {

    /**
     * This function fetches the images based on the provided query.
     * It first tries to fetch data from the local database.
     * If the data is not available or needs to be refreshed, it fetches data from the API and stores it in the database.
     */
    override suspend fun getImages(query: String): List<ImageDetailsDomainModel> {
        val localData = imageDetailsDao.getAllImageDetails(query)
        return when {
            localData.isNullOrEmpty() -> {
                fetchAndPersistData(query)
            }

            isRefreshNeeded(localData[0].createdAt) -> {
                imageDetailsDao.deleteBySearchQuery(query)
                fetchAndPersistData(query)
            }

            else -> {
                localData.map { data -> imageDetailsDatabaseToDomainMapper.toDomain(data) }
            }

        }
    }

    private fun isRefreshNeeded(lastFetchTimeInSeconds: Long): Boolean {
        return getCurrentTimeInSeconds() - lastFetchTimeInSeconds >= FETCH_INTERVAL_IN_SECONDS
    }

    private fun getCurrentTimeInSeconds() = System.currentTimeMillis() / 1000L

    private suspend fun fetchAndPersistData(query: String): List<ImageDetailsDomainModel> {
        val imageApiResponse = apiService.getAllImages(query = query)
        imageApiResponse.images?.map { imageDetailsApiModel ->
            imageDetailsApiToDatabaseMapper.toDatabase(
                MapperInput(imageDetailsApiModel, query, getCurrentTimeInSeconds())
            )
        }?.let { imageDetailsEntity ->
            imageDetailsDao.insertAll(imageDetailsEntity)
        }
        return imageApiResponse.images?.map { imageDetailsApiModel ->
            imageDetailsApiToDomainMapper.toDomain(imageDetailsApiModel)
        } ?: emptyList()
    }

}