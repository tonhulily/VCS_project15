package com.example.vcs_project15.presentation.home

import android.net.Uri
data class HomeState(
    val imageUri: Uri? = null,
    val imageBase64: String? = null,
    val searchQuery: String? = null,
    val labels: List<String> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)