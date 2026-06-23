package com.example.vcs_project15.data.remote.dto.vision
data class VisionRequest(
    val requests: List<Request>
)
data class Request(
    val image: Image,
    val features: List<Feature>
)
data class Image(
    val content: String
)
data class Feature(
    val type: String,
    val maxResults: Int = 10
)