package com.example.vcs_project15.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage

import com.example.vcs_project15.domain.model.SearchImage
import com.example.vcs_project15.utils.BrowserUtils
@Composable
fun SearchImageCard(
    item: SearchImage
) {
    val context = LocalContext.current
    var showDialog by remember {
        mutableStateOf(
            false
        )
    }
    if (showDialog) {
        ImageViewerDialog(
            imageUrl = item.imageUrl,
            onDismiss = {
                showDialog = false
            }
        )
    }
    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        modifier =
            Modifier.clickable {
                BrowserUtils.openUrl(
                    context,
                    item.imageUrl
                )
            }
    ) {
        Column {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clickable {
                            showDialog = true
                        }
            )
            Text(
                text = item.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}