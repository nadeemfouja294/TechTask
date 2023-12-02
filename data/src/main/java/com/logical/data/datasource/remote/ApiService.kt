package com.logical.data.datasource.remote

import com.logical.data.BuildConfig
import com.logical.data.models.ImagesApiModel
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

/**
 * This is a Retrofit service interface that provides access to the remote API.
 * It includes a method for fetching all images.
 */
interface ApiService {

    /**
     * This function fetches all images from the remote API.
     * It takes an API key, a query, and a language as parameters.
     * For security reasons, the API key is provided by the [BuildConfig].
     * The language defaults to the device's default language.
     */
    @GET("api/")
    suspend fun getAllImages(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("lang") language: String = Locale.getDefault().language
    ): ImagesApiModel
}