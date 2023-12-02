package com.logical.data.mappers

import com.logical.data.models.ImageDetailsApiModel
import com.logical.domain.models.ImageDetailsDomainModel
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ImageDetailsApiToDomainMapperTest(
    private val input: ImageDetailsApiModel,
    private val expectedResult: ImageDetailsDomainModel
) {

    companion object {
        private val input1 = ImageDetailsApiModel(
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
        private val expectedResult1 = ImageDetailsDomainModel(
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

        private val input2 = ImageDetailsApiModel(
            id = 2,
            pageURL = "https://example.com",
            type = "Scenery",
            tags = "nature, landscape",
            previewURL = "https://example.com/preview",
            webFormatURL = "https://example.com/webformat",
            largeImageURL = "https://example.com/large",
            downloads = 50,
            likes = 10,
            comments = 5,
            userId = 123888,
            user = "johndoe",
            userImageURL = "https://example.com/user"
        )
        private val expectedResult2 = ImageDetailsDomainModel(
            id = 2,
            pageURL = "https://example.com",
            type = "Scenery",
            tags = "nature, landscape",
            previewURL = "https://example.com/preview",
            webFormatURL = "https://example.com/webformat",
            largeImageURL = "https://example.com/large",
            downloads = 50,
            likes = 10,
            comments = 5,
            userId = 123888,
            user = "johndoe",
            userImageURL = "https://example.com/user"
        )

        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} When toDomain Then returns {1}")
        fun params() = listOf(
            arrayOf(input1, expectedResult1),
            arrayOf(input2, expectedResult2)
        )
    }

    private lateinit var classUnderTest: ImageDetailsApiToDomainMapper

    @Before
    fun setup() {
        classUnderTest = ImageDetailsApiToDomainMapper()
    }

    @Test
    fun `Given database model When toDomain then returns expected domain model`() {
        // When
        val actualResult = classUnderTest.toDomain(input)

        // Then
        assertEquals(expectedResult, actualResult)
    }
}