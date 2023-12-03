package com.logical.techtask.ui.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.logical.techtask.model.ImageDetailsUiModel
import com.logical.techtask.ui.detailscreen.DetailScreen
import com.logical.techtask.ui.navigation.Screens.Companion.IMAGE_ID
import com.logical.techtask.ui.mainlistscreen.ListScreen

/**
 * Composable function for app navigation using the Jetpack Compose Navigation library.
 *
 * @param navController The NavHostController responsible for managing navigation between destinations.
 */

@ExperimentalComposeUiApi
@ExperimentalLayoutApi
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.ImageList.route) {
        // e.g will add auth routes here if when we will extend project
        composable(Screens.ImageList.route) {
            ListScreen(navController)
        }
        composable(
            Screens.ImageDetail.route
        ) {
            val imageDetail =
                navController.previousBackStackEntry?.savedStateHandle?.get<ImageDetailsUiModel>(
                    IMAGE_ID
                )
            imageDetail?.let {
                DetailScreen(it, navController)
            }
        }
    }
}