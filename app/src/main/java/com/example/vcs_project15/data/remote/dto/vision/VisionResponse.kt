package com.example.vcs_project15.data.remote.dto.vision

data class VisionResponse(
    val responses: List<VisionAnnotationResponse>
)
data class VisionAnnotationResponse(
    val webDetection: WebDetection?
)