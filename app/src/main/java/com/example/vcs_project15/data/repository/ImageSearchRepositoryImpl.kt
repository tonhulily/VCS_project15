package com.example.vcs_project15.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.vcs_project15.BuildConfig
import com.example.vcs_project15.data.remote.api.SearchApi
import com.example.vcs_project15.data.remote.api.VisionApi
import com.example.vcs_project15.data.remote.dto.vision.*
import com.example.vcs_project15.domain.model.ImageLabel
import com.example.vcs_project15.domain.model.SearchImage
import com.example.vcs_project15.domain.repository.ImageSearchRepository
import com.example.vcs_project15.presentation.paging.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageSearchRepositoryImpl
@Inject constructor(
    private val visionApi: VisionApi,
    private val searchApi: SearchApi
) : ImageSearchRepository {
    override suspend fun detectLabels(
        imageBase64: String
    ): List<ImageLabel> {
        val request =
            VisionRequest(
                requests = listOf(
                    ImageRequest(
                        image =
                            ImageContent(
                                imageBase64
                            ),
                        features = listOf(
                            Feature(
                                type = "LABEL_DETECTION",
                                maxResults = 10
                            )
                        )
                    )
                )
            )

        val response =
            visionApi.detectLabels(
                request,
                BuildConfig.VISION_API_KEY
            )
        return response
            .responses
            .firstOrNull()
            ?.labelAnnotations
            ?.map {
                ImageLabel(
                    description = it.description,
                    score = it.score
                )
            }
            ?: emptyList()
    }
    override fun searchImages(
        keyword: String
    ): Flow<PagingData<SearchImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 3
            )
        ) {
            SearchPagingSource(
                searchApi,
                keyword
            )
        }.flow
    }
}