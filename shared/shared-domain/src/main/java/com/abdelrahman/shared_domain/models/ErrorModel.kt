package com.abdelrahman.shared_domain.models

import androidx.annotation.DrawableRes
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_domain.utils.Constants
import com.abdelrahman.shared_domain.utils.defaultString

sealed class ErrorModel(
    val errorMessage: TextWrapper?,
    val errorCode: TextWrapper?,
    val showRetryButton: Boolean,
    @DrawableRes val errorIcon: Int
) {

    data object NoInternetConnectModel : ErrorModel(
        TextWrapper.ResourceText(R.string.no_internet_connection),
        TextWrapper.ResourceText(R.string.failed_connect),
        true,
        R.drawable.ic_no_internet

    )

    data class ApiError(
        val message: TextWrapper?,
        val code: String?
    ) : ErrorModel(message, TextWrapper.StringText(code.defaultString()), true, R.drawable.ic_error)

    data object NoDataError : ErrorModel(
        TextWrapper.ResourceText(R.string.no_data_found),
        TextWrapper.ResourceText(R.string.no_result),
        false,
        R.drawable.ic_empty_result
    )
}