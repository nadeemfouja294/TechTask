package com.logical.data.repo

import com.logical.data.datasource.local.entities.ImageDetailsEntity
import com.logical.data.datasource.local.storage.ImageDetailsDao
import com.logical.data.datasource.remote.ApiService
import com.logical.data.mappers.ImageDetailsApiToDatabaseMapper
import com.logical.data.mappers.ImageDetailsApiToDomainMapper
import com.logical.data.mappers.ImageDetailsDatabaseToDomainMapper
import com.logical.data.mappers.MapperInput
import com.logical.data.models.ImageDetailsApiModel
import com.logical.data.models.ImagesApiModel
import com.logical.domain.models.ImageDetailsDomainModel
import com.logical.domain.repositories.ImagesRepository
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesRepositoryImpTest {

    private val searchQuery = "fruits"
    private val systemCurrentTimeInSeconds = System.currentTimeMillis() / 1000L

    private val imageDetailsApi = ImageDetailsApiModel(
        id = 1,
        pageURL = "https://example.com",
        type = "photo",
        tags = "nature, landscape",
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = 50,
        likes = 10,
        comments = 5,
        userId = 123,
        user = "johndoe",
        userImageURL = "https://example.com/user"
    )
    private val imageDetailsDomain = ImageDetailsDomainModel(
        id = 1,
        pageURL = "https://example.com",
        type = "photo",
        tags = "nature, landscape",
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = 50,
        likes = 10,
        comments = 5,
        userId = 123,
        user = "johndoe",
        userImageURL = "https://example.com/user"
    )

    private val imageDetailsEntity = ImageDetailsEntity(
        id = 1,
        pageURL = "https://example.com",
        type = "photo",
        tags = "nature, landscape",
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = 50,
        likes = 10,
        comments = 5,
        userId = 123,
        user = "johndoe",
        userImageURL = "https://example.com/user",
        searchQuery = "fruits",
        createdAt = systemCurrentTimeInSeconds
    )

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var imageDetailsDao: ImageDetailsDao

    @Mock
    lateinit var imageDetailsApiToDomainMapper: ImageDetailsApiToDomainMapper

    @Mock
    lateinit var imageDetailsApiToDatabaseMapper: ImageDetailsApiToDatabaseMapper

    @Mock
    lateinit var imageDetailsDatabaseToDomainMapper: ImageDetailsDatabaseToDomainMapper

    private lateinit var classUnderTest: ImagesRepository

    @Before
    fun setup() {
        classUnderTest = ImagesRepositoryImp(
            apiService = apiService,
            imageDetailsDao = imageDetailsDao,
            imageDetailsDatabaseToDomainMapper = imageDetailsDatabaseToDomainMapper,
            imageDetailsApiToDatabaseMapper = imageDetailsApiToDatabaseMapper,
            imageDetailsApiToDomainMapper = imageDetailsApiToDomainMapper
        )
    }

    @Test
    fun `When getImages called Then return listOf ImageDetailsDomainModel From Local DB`() {
        runBlocking {
            // Given
            val expectedResult = listOf(imageDetailsDomain)

            given(imageDetailsDao.getAllImageDetails(searchQuery))
                .willReturn(listOf(imageDetailsEntity))

            given(imageDetailsDatabaseToDomainMapper.toDomain(imageDetailsEntity))
                .willReturn(imageDetailsDomain)

            // When
            val actualResult = classUnderTest.getImages(searchQuery)

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `When getImages called Then return listOf ImageDetailsDomainModel From RemoteSource`() {
        runBlocking {
            // Given
            val expectedResult = listOf(imageDetailsDomain)

            given(imageDetailsDao.getAllImageDetails(searchQuery))
                .willReturn(emptyList())

            given(apiService.getAllImages(query = searchQuery))
                .willReturn(ImagesApiModel(images = listOf(imageDetailsApi)))

            given(imageDetailsApiToDomainMapper.toDomain(imageDetailsApi))
                .willReturn(imageDetailsDomain)

            lenient().`when`(
                imageDetailsApiToDatabaseMapper.toDatabase(
                    MapperInput(
                        apiImageDetails = imageDetailsApi,
                        searchQuery = searchQuery,
                        systemCurrentTimeInSeconds
                    )
                )
            ).thenReturn(imageDetailsEntity)

            // When
            val actualResult = classUnderTest.getImages(searchQuery)

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }
}