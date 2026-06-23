package com.example.vcs_project15.utils

import android.content.Context
import android.net.*

class NetworkMonitor(
    context: Context
) {
    private val manager =
        context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

    fun isConnected(): Boolean {
        val network =
            manager.activeNetwork
                ?: return false
        val capability =
            manager.getNetworkCapabilities(
                network
            )
                ?: return false
        return capability.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    }
}