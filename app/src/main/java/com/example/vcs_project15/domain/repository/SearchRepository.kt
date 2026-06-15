package com.example.vcs_project15.domain.repository

interface SearchRepository {
    suspend fun detectKeyword(
        imageBase64: String
    ): String
}