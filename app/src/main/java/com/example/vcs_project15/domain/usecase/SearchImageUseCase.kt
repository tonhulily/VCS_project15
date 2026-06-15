package com.example.vcs_project15.domain.usecase

import com.example.vcs_project15.domain.repository.ImageSearchRepository
import javax.inject.Inject

class SearchImageUseCase
@Inject constructor(
    private val repository: ImageSearchRepository
) {
    operator fun invoke(
        keyword: String
    ) = repository.searchImages(
        keyword
    )
}