package com.example.vcs_project15.domain.usecase

import com.example.vcs_project15.domain.repository.ImageSearchRepository
import javax.inject.Inject

class DetectWebUseCase
@Inject constructor(
    private val repository: ImageSearchRepository
) {
    suspend operator fun invoke(
        imageBase64: String
    ) =
        repository.detectWeb(
            imageBase64
        )
}