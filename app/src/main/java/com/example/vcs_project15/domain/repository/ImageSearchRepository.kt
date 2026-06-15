package com.example.vcs_project15.domain.repository

import androidx.paging.PagingData
import com.example.vcs_project15.domain.model.ImageLabel
import com.example.vcs_project15.domain.model.SearchImage
import kotlinx.coroutines.flow.Flow

interface ImageSearchRepository {
    suspend fun detectLabels(
        imageBase64: String
    ): List<ImageLabel>
    fun searchImages(
        keyword: String
    ): Flow<PagingData<SearchImage>>
}