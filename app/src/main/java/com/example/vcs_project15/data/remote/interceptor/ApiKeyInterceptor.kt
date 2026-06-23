package com.example.vcs_project15.data.remote.interceptor

import com.example.vcs_project15.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val request = chain.request()
        val url =
            request.url
                .newBuilder()
                .addQueryParameter(
                    "key",
                    BuildConfig.SEARCH_API_KEY
                )
                .addQueryParameter(
                    "cx",
                    BuildConfig.SEARCH_ENGINE_ID
                )
                .build()
        val newRequest =
            request.newBuilder()
                .url(url)
                .build()
        return chain.proceed(
            newRequest
        )
    }
}