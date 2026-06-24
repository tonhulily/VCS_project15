package com.example.vcs_project15.data.remote.dto.vision

data class VisionResponse(
    val responses: List<Response>
)

data class Response(
    val webDetection: WebDetection?,
    val labelAnnotations: List<LabelAnnotation>?
)

data class LabelAnnotation(
    val description: String,
    val score: Float
)