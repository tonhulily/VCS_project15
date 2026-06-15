package com.example.vcs_project15.data.remote.dto.vision

data class VisionRequest(
    val requests: List<ImageRequest>
)

data class ImageRequest(
    val image: ImageContent,
    val features: List<Feature>
)

data class ImageContent(
    val content: String
)

data class Feature(
    val type: String,
    val maxResults: Int
)