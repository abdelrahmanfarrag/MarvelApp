package com.abdelrahman.feature_characters_presentation.characterslist.ui.charactersearch

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import com.abdelrahman.feature_characters_domain.models.Character
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_domain.utils.defaultString
import com.abdelrahman.shared_presentation.ui.NetworkImage

@Composable
fun ItemCharacterSearch(
    modifier: Modifier = Modifier,
    character: Character? = null
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        NetworkImage(
            modifier = Modifier.size(dimensionResource(id = R.dimen.dimen_56)),
            imageUrl = character?.image?.portraitImage
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.dimen_8)),
            text = character?.name.defaultString(),
            maxLines = 1,
            color = colorResource(id = R.color.white),
            overflow = TextOverflow.Ellipsis
        )
    }
}