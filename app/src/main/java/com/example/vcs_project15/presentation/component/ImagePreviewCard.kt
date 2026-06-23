package com.example.vcs_project15.presentation.component

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImagePreviewCard(
    imageUri: Uri?
) {
    ElevatedCard(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(260.dp)

    ) {
        if (imageUri == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Choose Image"
                )
            }
        } else {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}