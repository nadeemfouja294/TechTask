package com.logical.data.di

import com.google.gson.Gson
import com.logical.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * This is a Hilt module used to provide dependencies for networking.
 * It is installed in the [SingletonComponent] which means the provided dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {
    /**
     * This function provides an instance of [Gson].
     * The provided [Gson] will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    /**
     * This function provides an instance of [HttpLoggingInterceptor].
     * The provided [HttpLoggingInterceptor] will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideHttpLogInterceptor() = HttpLoggingInterceptor().also {
        it.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    /**
     * This function provides an instance of [OkHttpClient].
     * The provided [OkHttpClient] will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    /**
     * This function provides an instance of [Retrofit].
     * The provided [Retrofit] will have a singleton scope.
     */
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}