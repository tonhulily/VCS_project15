package com.example.vcs_project15.data.remote.api

import com.example.vcs_project15.data.remote.dto.pexels.PexelsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PexelsApi {
    @GET("v1/search")
    suspend fun searchPhotos(
        @Header("Authorization")
        apiKey: String,
        @Query("query")
        query: String,
        @Query("page")
        page: Int,
        @Query("per_page")
        perPage: Int = 20
    ): PexelsResponse
}