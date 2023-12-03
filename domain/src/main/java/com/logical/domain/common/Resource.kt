package com.logical.domain.common

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    /**
     * A data class representing a successful state of the Resource.
     * @param <T>
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * A data class representing an error state of the Resource.
     * @param <T>
     */
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
}