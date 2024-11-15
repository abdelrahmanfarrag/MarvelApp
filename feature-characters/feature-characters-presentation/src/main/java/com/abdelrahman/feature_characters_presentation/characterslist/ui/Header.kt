package com.abdelrahman.feature_characters_presentation.characterslist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.abdelrahman.shared_domain.R

@Composable
fun Header(modifier: Modifier = Modifier, onSearchClick: () -> Unit = {}) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = dimensionResource(id = R.dimen.dimen_8)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(), painter = painterResource(id = R.drawable.ic_marvel),
            contentDescription = "Marvel icon"
        )
        Icon(
            modifier = Modifier
                .wrapContentSize()
                .padding(dimensionResource(id = R.dimen.dimen_8))
                .align(Alignment.CenterEnd)
                .clickable {
                    onSearchClick()
                },
            painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search",
            tint = Color.Red
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview_Header() {
    Header(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = dimensionResource(id = R.dimen.dimen_8))
            .background(color = colorResource(id = R.color.black))

    )
}