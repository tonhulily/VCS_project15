package com.example.vcs_project15.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vcs_project15.domain.model.SearchImage

@Composable
fun SearchImageCard(
    item: SearchImage
) {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            AsyncImage(
                model = item.thumbnailUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(180.dp)
            )
            Text(
                text = item.title,
                modifier = Modifier.padding(8.dp),
                maxLines = 2
            )
        }
    }
}