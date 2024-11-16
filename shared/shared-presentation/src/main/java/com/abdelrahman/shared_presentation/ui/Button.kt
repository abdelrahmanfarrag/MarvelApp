package com.abdelrahman.shared_presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    modifier: Modifier = Modifier,
    containerColor: Color = colorResource(com.abdelrahman.shared_domain.R.color.red),
    onButtonClick: (() -> Unit)? = null,
    roundedCornerSize: Int = com.abdelrahman.shared_domain.R.dimen.dimen_16,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit = {},
) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(dimensionResource(id = com.abdelrahman.shared_domain.R.dimen.dimen_8)),
        modifier = modifier,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = elevation
        ),
        contentPadding = PaddingValues(dimensionResource(id = roundedCornerSize)),
        onClick = { onButtonClick?.invoke() },
    ) {
        content()
    }
}