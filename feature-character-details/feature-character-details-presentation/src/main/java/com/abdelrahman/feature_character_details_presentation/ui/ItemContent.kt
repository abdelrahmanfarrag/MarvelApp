package com.abdelrahman.feature_character_details_presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.abdelrahman.shared_domain.R
import com.abdelrahman.feature_character_domain.models.CharacterDetailsSectionListItem
import com.abdelrahman.shared_domain.utils.defaultString
import com.abdelrahman.shared_presentation.ui.NetworkImage

@Composable
fun ItemContent(
    modifier: Modifier = Modifier,
    characterDetailsSectionListItem: CharacterDetailsSectionListItem? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.dimen_120))
                .width(
                    dimensionResource(id = R.dimen.dimen_80)
                ),
            imageUrl = characterDetailsSectionListItem?.image?.portraitImage,
        )
        Text(
            modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_80)),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            text = characterDetailsSectionListItem?.title.defaultString(),
            fontSize = dimensionResource(
                id = R.dimen.font_small
            ).value.sp,
            color = colorResource(id = R.color.white)
        )
    }

}