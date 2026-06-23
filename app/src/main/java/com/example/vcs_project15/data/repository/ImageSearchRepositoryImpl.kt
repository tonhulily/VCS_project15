package com.example.vcs_project15.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.vcs_project15.BuildConfig
import com.example.vcs_project15.data.remote.api.SearchApi
import com.example.vcs_project15.data.remote.api.VisionApi
import com.example.vcs_project15.data.remote.dto.vision.Feature
import com.example.vcs_project15.data.remote.dto.vision.Image
import com.example.vcs_project15.data.remote.dto.vision.Request
import com.example.vcs_project15.data.remote.dto.vision.VisionRequest
import com.example.vcs_project15.domain.model.WebDetectionResult
import com.example.vcs_project15.domain.repository.ImageSearchRepository
import com.example.vcs_project15.presentation.paging.SearchPagingSource
import javax.inject.Inject

class ImageSearchRepositoryImpl
@Inject constructor(
    private val visionApi: VisionApi,
    private val searchApi: SearchApi
) : ImageSearchRepository {
    override suspend fun detectWeb(
        imageBase64: String
    ): WebDetectionResult {
        val request =
            VisionRequest(
                requests = listOf(
                    Request(
                        image =
                            Image(
                                imageBase64
                            ),
                        features =
                            listOf(
                                Feature(
                                    type =
                                        "WEB_DETECTION"
                                )
                            )
                    )
                )
            )
        val response =
            visionApi.detectWeb(
                request,
                BuildConfig.VISION_API_KEY
            )
        val webDetection =
            response.responses
                .firstOrNull()
                ?.webDetection
        val query =
            webDetection
                ?.bestGuessLabels
                ?.firstOrNull()
                ?.label
                ?: ""
        val entities =
            webDetection
                ?.webEntities
                ?.mapNotNull {
                    it.description
                }
                ?: emptyList()
        return WebDetectionResult(
            query = query,
            entities = entities
        )
    }
    override fun searchImages(
        keyword: String
    ) =
        Pager(
            config =
                PagingConfig(
                    pageSize = 10
                ),
            pagingSourceFactory = {
                SearchPagingSource(
                    searchApi,
                    keyword
                )
            }
        ).flow
}