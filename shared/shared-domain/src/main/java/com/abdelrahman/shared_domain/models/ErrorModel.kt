package com.abdelrahman.shared_domain.models

import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_domain.utils.Constants

sealed class ErrorModel {

    data class NoInternetConnectModel(
        val errorMessage: TextWrapper = TextWrapper.ResourceText(R.string.no_internet_connection),
        val errorCode: Int = Constants.NO_INTERNET_CONNECTION_ERROR_CODE
    ) : ErrorModel()

    data class ApiError(
        val errorMessage: TextWrapper?,
        val errorCode: Int?
    ) : ErrorModel()
}