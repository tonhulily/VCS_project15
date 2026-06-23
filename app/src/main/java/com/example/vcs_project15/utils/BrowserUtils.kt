package com.example.vcs_project15.utils

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

object BrowserUtils {
    fun openUrl(
        context: Context,
        url: String
    ) {
        CustomTabsIntent
            .Builder()
            .build()
            .launchUrl(
                context,
                url.toUri()
            )
    }
}