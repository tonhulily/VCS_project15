package com.example.vcs_project15.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
@Composable
fun ImageViewerDialog(
    imageUrl: String,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        Color.Black
                    )
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}