package com.example.vcs_project15.presentation.camera

import android.net.Uri
import kotlinx.coroutines.flow.MutableStateFlow

object CameraResultManager {
    val imageUri =
        MutableStateFlow<Uri?>(
            null
        )
}