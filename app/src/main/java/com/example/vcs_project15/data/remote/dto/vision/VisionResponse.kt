package com.example.vcs_project15.data.remote.dto.vision

data class VisionResponse(
    val responses: List<VisionResult>
)
data class VisionResult(
    val labelAnnotations:
    List<LabelAnnotation>?
)

data class LabelAnnotation(
    val description: String,
    val score: Float
)