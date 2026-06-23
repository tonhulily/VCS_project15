package com.example.vcs_project15.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionButtons(
    onGalleryClick: () -> Unit,
    onCameraClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledTonalButton(
            modifier = Modifier.weight(1f),
            onClick = onGalleryClick
        ) {
            Icon(
                Icons.Default.Image,
                null
            )
            Spacer(
                Modifier.width(6.dp)
            )
            Text("Gallery")
        }
        FilledTonalButton(
            modifier = Modifier.weight(1f),
            onClick = onCameraClick
        ) {
            Icon(
                Icons.Default.CameraAlt,
                null
            )
            Spacer(
                Modifier.width(6.dp)
            )
            Text("Camera")
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = onSearchClick
        ) {
            Icon(
                Icons.Default.Search,
                null
            )
            Spacer(
                Modifier.width(6.dp)
            )
            Text("Search")
        }
    }
}