package com.example.vcs_project15.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RetryInterceptor : Interceptor {
    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        var retry = 0
        var response =
            chain.proceed(
                chain.request()
            )
        while (!response.isSuccessful && retry < 3) {
            retry++
            response.close()
            Thread.sleep(
                retry * 1000L
            )
            response =
                chain.proceed(
                    chain.request()
                )
        }
        return response
    }
}