package com.abdelrahman.shared_presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.abdelrahman.shared_domain.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_domain.utils.defaultString

@Composable
fun ErrorLayout(
    modifier: Modifier = Modifier,
    errorModel: ErrorModel? = null,
    onRetry: () -> Unit = {}
) {
    val context = LocalContext.current
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_16))
        ) {
            Image(
                painter = painterResource(id = errorModel?.errorIcon ?: R.drawable.ic_error),
                contentDescription = errorModel?.errorMessage?.getStringFromTextWrapper(context)
                    .defaultString()
            )
            Text(
                text = errorModel?.errorCode?.getStringFromTextWrapper(context).defaultString(),
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center
            )
            Text(
                text = errorModel?.errorMessage?.getStringFromTextWrapper(context).defaultString(),
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center
            )
            if (errorModel?.showRetryButton == true)
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = dimensionResource(id = R.dimen.dimen_32)),
                    onButtonClick = { onRetry() }) {
                    Text(text = stringResource(id = R.string.retry))
                }
        }
    }
}