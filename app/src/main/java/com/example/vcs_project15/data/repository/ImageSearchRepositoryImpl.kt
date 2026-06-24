package com.example.vcs_project15.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.vcs_project15.data.remote.api.PexelsApi
import com.example.vcs_project15.data.remote.api.VisionApi
import com.example.vcs_project15.data.remote.dto.vision.Feature
import com.example.vcs_project15.data.remote.dto.vision.Image
import com.example.vcs_project15.data.remote.dto.vision.Request
import com.example.vcs_project15.data.remote.dto.vision.VisionRequest
import com.example.vcs_project15.domain.model.WebDetectionResult
import com.example.vcs_project15.domain.repository.ImageSearchRepository
import com.example.vcs_project15.presentation.paging.SearchPagingSource
import javax.inject.Inject
import com.example.vcs_project15.BuildConfig

class ImageSearchRepositoryImpl
@Inject constructor(
    private val visionApi: VisionApi,
    private val pexelsApi: PexelsApi
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
                                    type = "LABEL_DETECTION",
                                    maxResults = 5
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
        val labels =
            response.responses
                .firstOrNull()
                ?.labelAnnotations
                ?: emptyList()
        val ignoredLabels =
            setOf(
                "Box",
                "Product",
                "Packaging",
                "Rectangle"
            )
        val query =
            labels
                .filter {
                    it.description !in ignoredLabels
                }
                .take(3)
                .joinToString(" ") {
                    it.description
                }
        val entities =
            labels.map {
                it.description
            }
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
                    pexelsApi,
                    keyword
                )
            }
        ).flow
}