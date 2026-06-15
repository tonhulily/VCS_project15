package com.example.vcs_project15.data.remote.dto.search
data class SearchResponse(
    val items: List<SearchItemDto>?
)
data class SearchItemDto(
    val title: String?,
    val link: String?,
    val image: SearchImageDto?
)
data class SearchImageDto(
    val thumbnailLink: String?
)