package com.example.vcs_project15.presentation.home

import android.net.Uri
import com.example.vcs_project15.domain.model.ImageLabel
data class HomeState(
    val imageUri: Uri? = null,
    val imageBase64: String? = null,
    val labels: List<ImageLabel> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
    val keyword: String? = null
)