package com.example.vcs_project15.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
object ImageUtils {
    fun uriToBase64(
        context: Context,
        uri: Uri
    ): String {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap =
            BitmapFactory.decodeStream(
                inputStream
            )
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            85,
            outputStream
        )
        return Base64.encodeToString(
            outputStream.toByteArray(),
            Base64.NO_WRAP
        )
    }
    fun compressBitmap(
        bitmap: Bitmap
    ): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            70,
            stream
        )
        return Base64.encodeToString(
            stream.toByteArray(),
            Base64.NO_WRAP
        )
    }
}