package com.abdelrahman.shared_presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AppDefaultProgressbar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            ,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
