package com.example.vcs_project15.data.remote.interceptor

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {
    fun create(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}