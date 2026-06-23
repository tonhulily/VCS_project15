package com.example.vcs_project15.data.remote.dto.search
data class SearchResponse(
    val items: List<SearchItem>?
)
data class SearchItem(
    val title: String?,
    val link: String?,
    val image: SearchImageInfo?
)
data class SearchImageInfo(
    val thumbnailLink: String?
)