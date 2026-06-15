package com.example.vcs_project15.di

import com.example.vcs_project15.data.repository.ImageSearchRepositoryImpl
import com.example.vcs_project15.domain.repository.ImageSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(
        repository: ImageSearchRepositoryImpl
    ): ImageSearchRepository
}