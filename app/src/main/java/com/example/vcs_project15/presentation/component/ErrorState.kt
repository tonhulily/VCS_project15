package com.example.vcs_project15.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = message
            )
            Button(
                onClick = onRetry
            ) {
                Text(
                    "Retry"
                )
            }
        }
    }
}