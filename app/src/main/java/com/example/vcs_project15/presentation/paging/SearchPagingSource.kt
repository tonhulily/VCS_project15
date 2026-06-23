package com.example.vcs_project15.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vcs_project15.BuildConfig
import com.example.vcs_project15.data.remote.api.SearchApi
import com.example.vcs_project15.domain.model.SearchImage

class SearchPagingSource(
    private val searchApi: SearchApi,
    private val keyword: String
) : PagingSource<Int, SearchImage>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SearchImage> {
        return try {
            val start = params.key ?: 1
            android.util.Log.e(
                "SEARCH_DEBUG",
                """
            keyword=$keyword
            start=$start
            apiKey=${BuildConfig.SEARCH_API_KEY}
            cx=${BuildConfig.SEARCH_ENGINE_ID}
            """.trimIndent()
            )
            val response =
                searchApi.searchImages(
                    apiKey = BuildConfig.SEARCH_API_KEY,
                    searchEngineId = BuildConfig.SEARCH_ENGINE_ID,
                    query = keyword,
                    start = 1
                )
            val images =
                response.items
                    ?.map {
                        SearchImage(
                            title = it.title.orEmpty(),
                            imageUrl = it.link.orEmpty(),
                            thumbnailUrl =
                                it.image?.thumbnailLink.orEmpty()
                        )
                    }
                    ?: emptyList()
            LoadResult.Page(
                data = images,
                prevKey =
                    if (start == 1) null
                    else start - 10,
                nextKey =
                    if (images.isEmpty()) null
                    else start + 10
            )
        } catch (e: Exception) {
            android.util.Log.e(
                "SEARCH_ERROR",
                e.stackTraceToString()
            )
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(
        state: PagingState<Int, SearchImage>
    ): Int? {
        return state.anchorPosition
            ?.let { position ->
                state.closestPageToPosition(
                    position
                )
                    ?.prevKey
                    ?.plus(10)
            }
    }
}