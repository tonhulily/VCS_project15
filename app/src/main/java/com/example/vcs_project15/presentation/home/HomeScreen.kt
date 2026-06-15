package com.example.vcs_project15.presentation.home

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import coil.compose.AsyncImage
import com.example.vcs_project15.utils.ImageUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val images =
        viewModel.images
            .collectAsLazyPagingItems()
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
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Image Search"
                    )
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns =
                GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }
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
                    onCameraClick = {
                        // Step 4 CameraScreen
                    },
                    onSearchClick = {
                        viewModel.detectImage()
                    }
                )
            }

            state.keyword?.let { keyword ->
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    KeywordCard(
                        keyword
                    )
                }
            }
            if (images.loadState.refresh is LoadState.Loading) {
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            if (images.itemCount == 0 && images.loadState.refresh is LoadState.NotLoading
                && state.keyword != null) {
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    EmptyResult()
                }
            }
            items(
                count =
                    images.itemCount
            ) { index ->
                images[index]?.let {
                    SearchImageCard(
                        item = it
                    )
                }
            }
            if (images.loadState.append is LoadState.Loading) {
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                Spacer(
                    modifier = Modifier.height(32.dp)
                )
            }
        }
    }
}

@Composable
fun ImagePreviewCard(
    imageUri: Uri?
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)

    ) {
        if (imageUri == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Choose an image"
                )
            }
        } else {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ActionButtons(
    onGalleryClick: () -> Unit,
    onCameraClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledTonalButton(
            modifier = Modifier.weight(1f),
            onClick = onGalleryClick
        ) {
            Icon(
                Icons.Default.Image,
                null
            )
            Spacer(
                Modifier.width(6.dp)
            )
            Text("Gallery")
        }
        FilledTonalButton(
            modifier = Modifier.weight(1f),
            onClick = onCameraClick

        ) {
            Icon(
                Icons.Default.CameraAlt,
                null
            )
            Spacer(
                Modifier.width(6.dp)
            )
            Text("Camera")
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = onSearchClick
        ) {
            Icon(
                Icons.Default.Search,
                null
            )
            Spacer(
                Modifier.width(6.dp)
            )
            Text("Search")
        }
    }
}
@Composable
fun KeywordCard(

    keyword: String

) {

    ElevatedCard(

        modifier =
            Modifier.fillMaxWidth()

    ) {

        Column(

            modifier =
                Modifier.padding(16.dp)

        ) {

            Text(

                text =
                    "Detected Keyword",

                style =
                    MaterialTheme
                        .typography
                        .labelLarge
            )

            Spacer(
                Modifier.height(6.dp)
            )

            Text(

                text = keyword,

                style =
                    MaterialTheme
                        .typography
                        .headlineSmall
            )
        }
    }
}
@Composable
fun EmptyResult() {

    Box(

        modifier = Modifier

            .fillMaxWidth()

            .padding(32.dp),

        contentAlignment =
            Alignment.Center

    ) {

        Text(
            text = "No result found"
        )
    }
}