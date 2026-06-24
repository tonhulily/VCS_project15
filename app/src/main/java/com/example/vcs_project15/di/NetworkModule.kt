package com.example.vcs_project15.di

import com.example.vcs_project15.data.remote.api.PexelsApi
import com.example.vcs_project15.data.remote.api.VisionApi
import com.example.vcs_project15.data.remote.interceptor.LoggingInterceptor
import com.example.vcs_project15.data.remote.interceptor.RetryInterceptor
import com.example.vcs_project15.data.remote.service.RetrofitProvider
import com.example.vcs_project15.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton
@Module
@InstallIn(
    SingletonComponent::class
)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                RetryInterceptor()
            )
            .addInterceptor(
                LoggingInterceptor.create()
            )
            .build()
    }
    @Provides
    @Singleton
    fun provideVisionApi(
        client: OkHttpClient
    ): VisionApi {
        return RetrofitProvider
            .create(
                Constants.VISION_BASE_URL,
                client
            )
            .create(
                VisionApi::class.java
            )
    }
    @Provides
    @Singleton
    fun providePexelsApi(
        client: OkHttpClient
    ): PexelsApi {
        return RetrofitProvider
            .create(
                Constants.PEXELS_BASE_URL,
                client
            )
            .create(
                PexelsApi::class.java
            )
    }
}