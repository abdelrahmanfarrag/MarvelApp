package com.abdelrahman.feature_character_details_presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.abdelrahman.feature_character_details_presentation.R
import com.abdelrahman.feature_character_details_presentation.models.CharacterDetailsData
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
        }

}