package com.logical.techtask.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.logical.techtask.ui.navigation.AppNavigation
import com.logical.techtask.ui.theme.TechTaskTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the app. Handles the navigation and theme setup.
 *
 * The `MainActivity` class is the entry point of the app. It extends `ComponentActivity`
 * and sets the main content using `setContent` function. It also configures the navigation
 * using the `AppNavigation` composable function and sets up the app's theme using `TechTaskTheme`.
 * Additionally, it handles the splash screen setup and system window fitting for devices with
 * transparent system bars.
 */
@ExperimentalMaterialApi
@ExperimentalLayoutApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            val navController = rememberNavController()
            TechTaskTheme {
                // A surface container using the 'background' color from the theme
                androidx.compose.material.Surface(color = androidx.compose.material.MaterialTheme.colors.background) {
                    AppNavigation(navController)
                }
            }
        }
    }
}