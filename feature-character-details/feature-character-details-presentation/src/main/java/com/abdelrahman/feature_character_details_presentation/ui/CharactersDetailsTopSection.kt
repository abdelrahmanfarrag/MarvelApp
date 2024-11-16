package com.abdelrahman.feature_character_details_presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_domain.models.Image
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailsTopSection(
    modifier: Modifier = Modifier,
    image: Image? = null,
    onBackClick: () -> Unit = {}
) {
    Box(modifier = modifier) {
        GlideImage(
            modifier = Modifier
                .fillMaxSize()
                .height(dimensionResource(id = R.dimen.dimen_280)),
            model = image?.landscapeImage,
            contentScale = ContentScale.FillBounds,
            contentDescription = image?.landscapeImage.toString()
        )
        Icon(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = dimensionResource(id = R.dimen.dimen_16),
                    top = dimensionResource(
                        id = R.dimen.dimen_16
                    )
                )
                .clickable {
                    onBackClick()
                },
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = R.drawable.ic_back_arrow.toString(),
            tint = colorResource(id = R.color.white)
        )
    }
}