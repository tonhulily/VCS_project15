package com.example.vcs_project15.domain.repository

import androidx.paging.PagingData
import com.example.vcs_project15.domain.model.SearchImage
import com.example.vcs_project15.domain.model.WebDetectionResult
import kotlinx.coroutines.flow.Flow
interface ImageSearchRepository {
    suspend fun detectWeb(
        imageBase64: String
    ): WebDetectionResult
    fun searchImages(
        keyword: String
    ): Flow<PagingData<SearchImage>>
}