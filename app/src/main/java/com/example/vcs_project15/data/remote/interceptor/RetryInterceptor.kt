package com.example.vcs_project15.data.remote.interceptor

import com.example.vcs_project15.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.math.pow

class RetryInterceptor : Interceptor {
    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val request = chain.request()
        var tryCount = 0
        var response = chain.proceed(request)
        while (shouldRetry(response.code) && tryCount < Constants.MAX_RETRY) {
            response.close()
            tryCount++
            val delayMillis = (1000 * 2.0.pow(tryCount.toDouble())).toLong()
            Thread.sleep(delayMillis)
            response = chain.proceed(request)
        }
        return response
    }
    private fun shouldRetry(
        code: Int
    ): Boolean {
        return code == 408 || code == 429 || code == 502 || code == 503 || code == 504
    }
}