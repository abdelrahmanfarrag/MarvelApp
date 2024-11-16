package com.abdelrahman.shared_domain.models

import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_domain.utils.Constants

sealed class ErrorModel(val errorMessage: TextWrapper?, val errorCode: String?) {

    data class NoInternetConnectModel(
        val message: TextWrapper = TextWrapper.ResourceText(R.string.no_internet_connection),
        val code: String = Constants.NO_INTERNET_CONNECTION_ERROR_CODE.toString()
    ) : ErrorModel(message, code)

    data class ApiError(
        val message: TextWrapper?,
        val code: String?
    ) : ErrorModel(message, code)
}