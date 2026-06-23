package com.example.vcs_project15.presentation.navigation
sealed class Screen(
    val route: String
) {
    data object Home : Screen("home")
    data object Camera : Screen("camera")
}