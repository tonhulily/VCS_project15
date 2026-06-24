package com.example.vcs_project15.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vcs_project15.BuildConfig
import com.example.vcs_project15.data.remote.api.PexelsApi
import com.example.vcs_project15.domain.model.SearchImage

class SearchPagingSource(
    private val pexelsApi: PexelsApi,
    private val keyword: String
) : PagingSource<Int, SearchImage>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SearchImage> {
        return try {
            val page = params.key ?: 1
            val response =
                pexelsApi.searchPhotos(
                    apiKey = BuildConfig.PEXELS_API_KEY,
                    query = keyword,
                    page = page,
                    perPage = 20
                )

            val images =
                response.photos.map {
                    SearchImage(
                        title = keyword,
                        imageUrl = it.src.large,
                        thumbnailUrl = it.src.medium
                    )
                }
            LoadResult.Page(
                data = images,
                prevKey =
                    if (page == 1) null
                    else page - 1,
                nextKey =
                    if (images.isEmpty()) null
                    else page + 1
            )
        } catch (e: Exception) {
            android.util.Log.e(
                "PEXELS_ERROR",
                e.stackTraceToString()
            )
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(
        state: PagingState<Int, SearchImage>
    ): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)
                ?.prevKey
                ?.plus(1)
                ?: state.closestPageToPosition(position)
                    ?.nextKey
                    ?.minus(1)
        }
    }
}