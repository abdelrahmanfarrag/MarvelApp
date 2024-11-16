package com.abdelrahman.feature_character_details_presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abdelrahman.shared_domain.R
import com.abdelrahman.feature_character_domain.models.CharacterDetailsSection

@Composable
fun DetailsSectionList(
    modifier: Modifier = Modifier, detailsSection: CharacterDetailsSection? = null
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8)),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(
                id = detailsSection?.sectionTitle ?: R.string.name
            ),
            fontSize = dimensionResource(id = R.dimen.font_small).value.sp,
            color = colorResource(id = R.color.lightRed),
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(end = dimensionResource(id = R.dimen.dimen_16)),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_16)
            )
        ) {
            items(
                detailsSection?.sectionsList ?: arrayListOf()
            ) { characterDetailsSectionListItem ->
                ItemContent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(bottom = dimensionResource(id = R.dimen.dimen_8)),
                    characterDetailsSectionListItem = characterDetailsSectionListItem
                )
            }
        }

    }

}