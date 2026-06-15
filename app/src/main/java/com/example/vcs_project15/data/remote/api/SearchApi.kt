package com.example.vcs_project15.data.remote.api

import com.example.vcs_project15.data.remote.dto.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("customsearch/v1")
    suspend fun searchImages(
        @Query("key")
        apiKey: String,
        @Query("cx")
        searchEngineId: String,
        @Query("q")
        query: String,
        @Query("searchType")
        searchType: String = "image",
        @Query("start")
        start: Int
    ): SearchResponse
}