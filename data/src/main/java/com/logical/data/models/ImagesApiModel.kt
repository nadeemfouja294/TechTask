package com.logical.data.models

import com.google.gson.annotations.SerializedName

/**
 * This data class represents the model for the images API response.
 * It contains a list of [ImageDetailsApiModel] objects.
 * The @SerializedName annotation is used to specify the key name in the JSON object because it's different from the variable name.
 */
data class ImagesApiModel(
    @SerializedName("hits")
    val images: List<ImageDetailsApiModel>?
)