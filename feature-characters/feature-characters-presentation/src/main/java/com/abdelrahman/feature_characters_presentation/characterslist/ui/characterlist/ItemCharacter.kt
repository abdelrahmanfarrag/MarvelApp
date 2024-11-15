@file:OptIn(ExperimentalGlideComposeApi::class)

package com.abdelrahman.feature_characters_presentation.characterslist.ui.characterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.abdelrahman.feature_characters_domain.models.Character
import com.abdelrahman.shared_domain.utils.defaultString
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun ItemCharacter(
    modifier: Modifier = Modifier,
    character: Character = Character(null, null, null, null, null)
) {

    Box(
        modifier = modifier
            .wrapContentSize()
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = com.abdelrahman.shared_domain.R.dimen.characterHeight)),
            model = character.image?.landscapeImage,
            contentScale = ContentScale.FillBounds,
            contentDescription = character.image?.landscapeImage
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = com.abdelrahman.shared_domain.R.dimen.characterHeight))
                .background(
                    brush = Brush.linearGradient(
                        colors = arrayListOf(
                            colorResource(
                                id = com.abdelrahman.shared_domain.R.color.black
                            ).copy(
                                alpha = 0.1f
                            ), colorResource(id = com.abdelrahman.shared_domain.R.color.black).copy(
                                alpha = 0.3f
                            ), colorResource(id = com.abdelrahman.shared_domain.R.color.black).copy(
                                alpha = 0.5f
                            )
                        )
                    )
                )

        )
        Text(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = com.abdelrahman.shared_domain.R.dimen.dimen_16),
                    bottom = dimensionResource(
                        id = com.abdelrahman.shared_domain.R.dimen.dimen_16
                    )
                )
                .wrapContentSize()
                .align(Alignment.BottomStart)
                .background(
                    color = colorResource(id = com.abdelrahman.shared_domain.R.color.white),
                    shape = RoundedCornerShape(
                        dimensionResource(id = com.abdelrahman.shared_domain.R.dimen.dimen_4)
                    )
                ),
            text = character.name.defaultString(),
            color = colorResource(id = com.abdelrahman.shared_domain.R.color.black)
        )

    }
}