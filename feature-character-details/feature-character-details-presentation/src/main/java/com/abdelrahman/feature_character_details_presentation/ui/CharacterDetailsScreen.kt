package com.abdelrahman.feature_character_details_presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsData
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_presentation.ui.AppDefaultProgressbar
import com.abdelrahman.shared_presentation.ui.LoadingTypes

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    characterDetailsData: CharacterDetailsData? = null,
    loadingTypes: LoadingTypes = LoadingTypes.NONE
) {
    if (loadingTypes == LoadingTypes.NORMAL_PROGRESS)
        AppDefaultProgressbar(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        )
    else
        LazyColumn(modifier = modifier) {
            item {
                CharacterDetailsTopSection(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .wrapContentHeight(),
                    image = characterDetailsData?.image
                ) {

                }
            }
            if (!characterDetailsData?.name.isNullOrEmpty()) {
                item {
                    DetailsSection(
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.dimen_32))
                            .fillParentMaxWidth()
                            .wrapContentHeight()
                            .padding(start = dimensionResource(id = R.dimen.dimen_16))
                            .background(color = Color.Transparent),
                        sectionTitle = R.string.name,
                        sectionDescription = characterDetailsData?.name
                    )
                }
            }
            if (!characterDetailsData?.description.isNullOrEmpty()) {
                item {
                    DetailsSection(
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.dimen_32))
                            .fillParentMaxWidth()
                            .wrapContentHeight()
                            .padding(start = dimensionResource(id = R.dimen.dimen_16))
                            .background(color = Color.Transparent),
                        sectionTitle = R.string.description,
                        sectionDescription = characterDetailsData?.description
                    )
                }
            }
            items(characterDetailsData?.characterDetailsSections ?: arrayListOf()) {
                DetailsSectionList(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.dimen_32))
                        .padding(start = dimensionResource(id = R.dimen.dimen_16))
                        .fillParentMaxWidth()
                        .wrapContentHeight(),
                    detailsSection = it
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.dimen_16),
                        top = dimensionResource(id = R.dimen.dimen_16),
                        bottom = dimensionResource(id = R.dimen.dimen_16)
                    ),
                    text = stringResource(
                        id = R.string.related_links
                    ),
                    fontSize = dimensionResource(id = R.dimen.font_small).value.sp,
                    color = colorResource(id = R.color.lightRed),
                    fontWeight = FontWeight.Bold
                )
            }
            items(characterDetailsData?.extraData ?: arrayListOf()) {
                if (!it.url.isNullOrEmpty()) {
                    ItemRelatedLinks(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .wrapContentHeight()
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.dimen_16),
                                vertical = dimensionResource(id = R.dimen.dimen_8)
                            )
                            .clickable {
                            },
                        extraData = it
                    )
                }
            }
        }

}