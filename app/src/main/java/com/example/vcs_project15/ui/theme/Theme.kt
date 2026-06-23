package com.example.vcs_project15.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
private val DarkColors =
    darkColorScheme(
        primary = PurpleStart,
        secondary = CyanAccent,
        background = DarkBackground,
        surface = DarkSurface
    )
private val LightColors =
    lightColorScheme(
        primary = PurpleStart,
        secondary = CyanAccent,
        background = LightBackground
    )
@Composable
fun VCSProject15Theme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme =
            if (darkTheme) DarkColors
            else LightColors,
        content = content
    )
}