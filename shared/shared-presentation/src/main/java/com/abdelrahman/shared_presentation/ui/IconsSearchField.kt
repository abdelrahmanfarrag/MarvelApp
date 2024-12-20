package com.abdelrahman.shared_presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.abdelrahman.shared_domain.R

@Composable
fun IconSearchField(
    icon: Int = R.drawable.ic_search,
    tintColor: Color = colorResource(R.color.black),
    onSearchClick: () -> Unit = {}
) {
    Icon(
        painterResource(id = icon),
        contentDescription = stringResource(id = R.string.search),
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.dimen_20))
            .clickable { onSearchClick() },
        tint = tintColor,
    )
}