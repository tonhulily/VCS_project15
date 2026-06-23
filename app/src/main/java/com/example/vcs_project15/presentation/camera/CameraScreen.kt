package com.example.vcs_project15.presentation.camera

import android.content.ContentValues
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun CameraScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var imageCapture by remember {
        mutableStateOf<ImageCapture?>(
            null
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Camera"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
        ) {
            AndroidView(
                factory = {
                    val previewView = PreviewView(context)
                    val cameraProviderFuture =
                        ProcessCameraProvider
                            .getInstance(
                                context
                            )
                    cameraProviderFuture
                        .addListener({
                            val cameraProvider = cameraProviderFuture.get()
                            val preview = Preview.Builder().build()
                            preview.surfaceProvider = previewView.surfaceProvider
                            imageCapture =
                                ImageCapture
                                    .Builder()
                                    .build()
                            cameraProvider.unbindAll()
                            cameraProvider
                                .bindToLifecycle(
                                    context as androidx.lifecycle.LifecycleOwner,
                                    CameraSelector.DEFAULT_BACK_CAMERA,
                                    preview,
                                    imageCapture
                                )
                        },
                            ContextCompat
                                .getMainExecutor(
                                    context
                                )
                        )
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )
            FloatingActionButton(
                onClick = {
                    val capture = imageCapture ?: return@FloatingActionButton
                    val name = "IMG_${System.currentTimeMillis()}"
                    val contentValues =
                        ContentValues()
                            .apply {
                                put(
                                    MediaStore.Images.Media.DISPLAY_NAME,
                                    name
                                )
                                put(
                                    MediaStore.Images.Media.MIME_TYPE,
                                    "image/jpeg"
                                )
                            }
                    val output =
                        ImageCapture
                            .OutputFileOptions
                            .Builder(
                                context.contentResolver,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                contentValues
                            )
                            .build()
                    capture.takePicture(
                        output,
                        ContextCompat
                            .getMainExecutor(
                                context
                            ),
                        object :
                            ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(
                                outputFileResults:
                                ImageCapture.OutputFileResults
                            ) {
                                CameraResultManager
                                    .imageUri
                                    .value =
                                    outputFileResults.savedUri
                                onBack()
                            }
                            override fun onError(
                                exception:
                                ImageCaptureException
                            ) {
                            }
                        }
                    )
                },
                modifier =
                    Modifier
                        .align(
                            Alignment.BottomCenter
                        )
                        .padding(
                            24.dp
                        )
            ) {
                Icon(
                    Icons.Default.Camera,
                    null
                )
            }
        }
    }
}