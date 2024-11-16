package com.abdelrahman.shared_presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abdelrahman.shared_domain.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    val context = LocalContext.current
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .error(R.drawable.ic_no_image)
            .placeholder(R.drawable.ic_no_image)
            .build(),
        contentScale = contentScale,
        contentDescription = imageUrl
    )
}