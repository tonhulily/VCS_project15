package com.example.vcs_project15.presentation.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.vcs_project15.ui.theme.*

private val DarkScheme = darkColorScheme(
    primary = PurplePrimary,
    secondary = CyanAccent,
    tertiary = OrangeAccent,
    background = Background,
    surface = Surface
)

@Composable
fun ImageSearchTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkScheme,
        content = content
    )
}