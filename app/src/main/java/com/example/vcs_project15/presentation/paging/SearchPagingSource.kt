package com.example.vcs_project15.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.vcs_project15.data.remote.api.SearchApi
import com.example.vcs_project15.domain.model.SearchImage
import com.example.vcs_project15.BuildConfig

class SearchPagingSource(
    private val searchApi: SearchApi,
    private val keyword: String
) : PagingSource<Int, SearchImage>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SearchImage> {
        return try {
            val start = params.key ?: 1

            val response =
                searchApi.searchImages(
                    apiKey = BuildConfig.SEARCH_API_KEY,
                    searchEngineId = BuildConfig.SEARCH_ENGINE_ID,
                    query = keyword,
                    start = start
                )
            val items =
                response.items
                    ?.map {
                        SearchImage(
                            title = it.title ?: "",
                            imageUrl = it.link ?: "",
                            thumbnailUrl =
                                it.image
                                    ?.thumbnailLink
                                    ?: ""
                        )
                    }
                    ?: emptyList()
            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey =
                    if (items.isEmpty()) null
                    else start + 10
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(
        state: PagingState<Int, SearchImage>
    ): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)
                ?.nextKey
                ?.minus(10)
        }
    }
}