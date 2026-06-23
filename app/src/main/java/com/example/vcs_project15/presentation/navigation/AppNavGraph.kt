package com.example.vcs_project15.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

import com.example.vcs_project15.presentation.camera.CameraScreen
import com.example.vcs_project15.presentation.home.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            Screen.Home.route
        ) {
            HomeScreen(
                openCamera = {
                    navController.navigate(
                        Screen.Camera.route
                    )
                }
            )
        }
        composable(
            Screen.Camera.route
        ) {
            CameraScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}