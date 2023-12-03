package com.logical.domain.usecases

import android.content.res.Resources
import com.logical.domain.R
import com.logical.domain.common.Resource
import com.logical.domain.models.ImageDetailsDomainModel
import com.logical.domain.repositories.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @constructor [ImagesRepository], [Resources]
 * This class consumes [ImagesRepository] to fetch images based on a query and emits the result as a [Resource].
 */
class ImagesUseCase @Inject constructor(
    private val imagesRepository: ImagesRepository,
    private val resources: Resources
) {
    /**
     * This function invokes the [ImagesRepository] to fetch images based on the provided query.
     * It emits a [Resource.Success] if the images are fetched successfully.
     * It emits a [Resource.Error] if an [HttpException] or [IOException] occurs during the fetch operation.
     */
    operator fun invoke(query: String): Flow<Resource<List<ImageDetailsDomainModel>>> =
        flow {
            try {
                val images = imagesRepository.getImages(query)
                emit(Resource.Success(images))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage))
            } catch (e: IOException) {
                emit(Resource.Error(resources.getString(R.string.no_internet_message)))
            }
        }
}