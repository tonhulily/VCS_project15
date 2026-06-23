package com.example.vcs_project15.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QueryCard(
    query: String
) {
    ElevatedCard {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Detected Query"
            )
            Spacer(
                Modifier.height(
                    8.dp
                )
            )
            Text(
                text = query,
                style =
                    MaterialTheme
                        .typography
                        .headlineSmall
            )
        }
    }
}