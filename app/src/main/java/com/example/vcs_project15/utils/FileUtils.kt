package com.example.vcs_project15.utils

import android.content.Context
import java.io.File

object FileUtils {
    fun createImageFile(
        context: Context
    ): File {
        return File.createTempFile(
            "camera_",
            ".jpg",
            context.cacheDir
        )
    }
}