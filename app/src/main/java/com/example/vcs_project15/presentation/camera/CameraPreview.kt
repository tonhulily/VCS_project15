package com.example.vcs_project15.presentation.camera

import android.annotation.SuppressLint
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun CameraPreview(
    previewView: PreviewView
) {
    AndroidView(
        factory = {
            previewView
        }
    )
}