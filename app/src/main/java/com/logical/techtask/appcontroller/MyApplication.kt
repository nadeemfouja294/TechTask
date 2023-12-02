package com.logical.techtask.appcontroller

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import dagger.hilt.android.HiltAndroidApp

/**
 * This is the main application class for the app.
 * It extends [Application] and is annotated with [HiltAndroidApp] to enable dependency injection with Hilt.
 */
@HiltAndroidApp
class MyApplication : Application() {

    /**
     * This method is called when the application is starting, before any activity, service, or receiver objects have been created.
     * It initializes Coil, an image loading library for Android, with custom settings.
     */
    override fun onCreate() {
        super.onCreate()
        initCoil()
    }

    /**
     * This method initializes Coil with custom settings.
     * It sets up a memory cache and a disk cache, each with a maximum size of 50% of available space.
     * It also enables SVG decoding and caching policies.
     */
    private fun initCoil() {
        Coil.setImageLoader(
            ImageLoader.Builder(applicationContext)
                .memoryCache {
                    MemoryCache.Builder(applicationContext)
                        .maxSizePercent(0.5)
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(applicationContext.cacheDir.resolve("image_cache"))
                        .maxSizePercent(0.5)
                        .build()
                }
                .components {
                    add(SvgDecoder.Factory())
                }
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build()
        )
    }
}