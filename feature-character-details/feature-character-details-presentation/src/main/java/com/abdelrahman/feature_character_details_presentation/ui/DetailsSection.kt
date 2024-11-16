package com.abdelrahman.feature_character_details_presentation.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.abdelrahman.shared_domain.utils.defaultString

@Composable
fun DetailsSection(
    modifier: Modifier = Modifier,
    @StringRes sectionTitle: Int = R.string.no_internet_connection,
    sectionDescription: String? = ""
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8)),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(id = sectionTitle),
            fontSize = dimensionResource(id = R.dimen.font_small).value.sp,
            color = colorResource(id = R.color.lightRed),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = sectionDescription.defaultString(),
            fontSize = dimensionResource(id = R.dimen.font_large).value.sp,
            color = colorResource(id = R.color.white)
        )
    }
}