package com.example.vcs_project15.presentation.camera

import android.net.Uri

data class CameraState(
    val imageUri: Uri? = null,
    val isFlashEnabled: Boolean = false,
    val isFrontCamera: Boolean = false,
    val isCapturing: Boolean = false
)