package com.example.vcs_project15.data.remote.dto.vision

data class WebDetection(
    val webEntities: List<WebEntity>?,
    val bestGuessLabels: List<BestGuessLabel>?
)
data class WebEntity(
    val entityId: String?,
    val score: Double?,
    val description: String?
)
data class BestGuessLabel(
    val label: String?
)