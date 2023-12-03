package com.logical.techtask.ui.navigation

/**
 * Sealed class representing the available screens (destinations) in the app's navigation.
 * Each screen has a corresponding route that is used for navigation.
 *
 * @param route The unique identifier for the screen that will be used for navigation.
 */

sealed class Screens(val route: String) {
    // Argument Keys
    companion object {
        const val IMAGE_ID = "image_id"
    }

    object ImageList : Screens("image_list")
    object ImageDetail : Screens("image_detail")
}