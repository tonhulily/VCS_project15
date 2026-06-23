package com.example.vcs_project15.presentation.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.vcs_project15.presentation.camera.CameraResultManager
import com.example.vcs_project15.presentation.component.ActionButtons
import com.example.vcs_project15.presentation.component.ErrorState
import com.example.vcs_project15.presentation.component.ImagePreviewCard
import com.example.vcs_project15.presentation.component.LoadingState
import com.example.vcs_project15.presentation.component.QueryCard
import com.example.vcs_project15.presentation.component.SearchImageCard
import com.example.vcs_project15.utils.ImageUtils

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
    openCamera: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val cameraUri by CameraResultManager.imageUri.collectAsState()
    val state by viewModel.state.collectAsState()
    val images = viewModel.images.collectAsLazyPagingItems()
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts
                    .PickVisualMedia()
        ) { uri ->
            uri ?: return@rememberLauncherForActivityResult
            val base64 =
                ImageUtils.uriToBase64(
                    context,
                    uri
                )
            viewModel.setImage(
                uri,
                base64
            )
        }
    val snackbarHostState =
        remember {
            SnackbarHostState()
        }
    LaunchedEffect(
        cameraUri
    ) {
        cameraUri ?: return@LaunchedEffect
        val base64 =
            ImageUtils.uriToBase64(
                context,
                cameraUri!!
            )
        viewModel.setImage(
            cameraUri!!,
            base64
        )
        CameraResultManager
            .imageUri
            .value = null
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Google Lens Mini"
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                ImagePreviewCard(
                    state.imageUri
                )
            }
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                ActionButtons(
                    onGalleryClick = {
                        galleryLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts
                                    .PickVisualMedia
                                    .ImageOnly
                            )
                        )
                    },
                    onCameraClick = openCamera,
                    onSearchClick = {
                        viewModel.detectImage()
                    }
                )
            }
            state.searchQuery
                ?.let {
                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        QueryCard(it)
                    }
                }
            if (images.loadState.refresh is LoadState.Loading) {
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    LoadingState()
                }
            }
            when (
                val state = images.loadState.append
            ) {
                is LoadState.Loading -> {
                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        LoadingState()
                    }
                }
                is LoadState.Error -> {
                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        ErrorState(
                            message = state.error.message ?: "Unknown",
                            onRetry = {
                                images.retry()
                            }
                        )
                    }
                }
                else -> Unit
            }
        }
        LaunchedEffect(
            Unit
        ) {
            viewModel.event.collect {
                when (it) {
                    is UiEvent.ShowError -> {
                        snackbarHostState
                            .showSnackbar(
                                it.message
                            )
                    }
                }
            }
        }
    }
}