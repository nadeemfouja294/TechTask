package com.logical.techtask.model

import com.logical.techtask.viewmodel.ViewState

/**
 * This is a data class that represents the state of the images view.
 * It extends [ViewState], which is a marker interface for all view state classes.
 * It contains all the necessary information about the current state of the view, including whether data is loading, the list of images, and any error message.
 */
data class ImagesViewState(
    val isDataLoading: Boolean,
    val images: List<ImageDetailsPresentationModel>,
    val errorMessage: String
) : ViewState

fun emptyViewState() =
    ImagesViewState(isDataLoading = false, images = emptyList(), errorMessage = "")