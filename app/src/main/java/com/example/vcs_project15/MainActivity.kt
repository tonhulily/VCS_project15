package com.example.vcs_project15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vcs_project15.presentation.home.HomeScreen
import com.example.vcs_project15.presentation.theme.ImageSearchTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearchTheme {
                HomeScreen()
            }
        }
    }
}