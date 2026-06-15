package com.example.vcs_project15.data.remote.api

import com.example.vcs_project15.data.remote.dto.vision.VisionRequest
import com.example.vcs_project15.data.remote.dto.vision.VisionResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface VisionApi {
    @POST("v1/images:annotate")
    suspend fun detectLabels(
        @Body request: VisionRequest,
        @Query("key")
        apiKey: String
    ): VisionResponse
}