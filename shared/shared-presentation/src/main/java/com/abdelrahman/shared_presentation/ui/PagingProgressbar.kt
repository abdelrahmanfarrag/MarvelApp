package com.abdelrahman.shared_presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.abdelrahman.shared_domain.R

fun LazyListScope.pagingProgressbar(shouldShow: Boolean = false) {
    if (shouldShow)
        item {
            Box(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .wrapContentHeight()
                    .background(color = colorResource(id = R.color.darkGrey))
                    .padding(
                        vertical = dimensionResource(
                            id = R.dimen.dimen_16
                        )
                    ), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
}