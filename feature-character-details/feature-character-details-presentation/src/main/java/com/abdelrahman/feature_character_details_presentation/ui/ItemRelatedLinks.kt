package com.abdelrahman.feature_character_details_presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.abdelrahman.shared_domain.models.ExtraData

@Composable
fun ItemRelatedLinks(modifier: Modifier = Modifier, extraData: ExtraData? = null) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(
                id = extraData?.sectionName ?: com.abdelrahman.shared_domain.R.string.comic_link
            ), color = colorResource(
                id = com.abdelrahman.shared_domain.R.color.white
            )
        )
        Icon(
            painter = painterResource(id = com.abdelrahman.shared_domain.R.drawable.ic_next),
            contentDescription = extraData?.url
        )
    }
}