package com.example.vcs_project15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import com.example.vcs_project15.presentation.navigation.AppNavGraph
import com.example.vcs_project15.presentation.theme.ImageSearchTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContent {
            ImageSearchTheme {
                AppNavGraph()
            }
        }
    }
}