package com.abdelrahman.api.validateresponse

import com.abdelrahman.api.models.ErrorResponse
import com.abdelrahman.api.models.ResponseState
import com.abdelrahman.api.networkcheck.ICheckNetworkAvailability
import com.abdelrahman.shared_domain.R
import com.abdelrahman.shared_domain.models.ErrorModel
import com.abdelrahman.shared_domain.models.TextWrapper
import com.abdelrahman.shared_domain.utils.Constants
import com.abdelrahman.shared_domain.utils.defaultString
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ValidateResponse @Inject constructor(
    private val iCheckNetworkAvailability: ICheckNetworkAvailability,
    private val mGson: Gson
) : IValidateResponse {

    override suspend fun <T> validateApiResponse(response: Response<T>): ResponseState<T> {
        return if (iCheckNetworkAvailability.isNetworkAvailable()) {
            try {
                val apiResponse = response.body()
                if (response.isSuccessful && apiResponse != null)
                    ResponseState.StateSuccess(apiResponse)
                else {
                    onErrorHappened(response.errorBody(), response.code())
                }
            } catch (httpException: HttpException) {
                onHttpExceptionHappens(httpException)
            } catch (exception: Exception) {
                onExceptionHappen(exception)
            } as ResponseState<T>
        } else
            ResponseState.StateError(ErrorModel.NoInternetConnectModel()) as ResponseState<T>
    }

    private fun onErrorHappened(responseBody: ResponseBody?, code: Int): ResponseState.StateError {
        val errorBody =
            mGson.fromJson(
                responseBody?.charStream(),
                ErrorResponse::class.java
            )
        return if (errorBody != null)
            ResponseState.StateError(
                errorModel = ErrorModel.ApiError(
                    errorMessage = TextWrapper.StringText(
                        errorBody.errorDescription.defaultString(),
                    ),
                    errorCode = errorBody.code
                )
            ) else {
            ResponseState.StateError(
                errorModel = ErrorModel.ApiError(
                    errorMessage = TextWrapper.ResourceText(R.string.something_went_wrong),
                    errorCode = code
                )
            )
        }
    }

    private fun onHttpExceptionHappens(httpException: HttpException): ResponseState.StateError {
        return ResponseState.StateError(
            ErrorModel.ApiError(
                TextWrapper.StringText(httpException.message()),
                httpException.code()
            )
        )
    }

    private fun onExceptionHappen(exception: Exception): ResponseState.StateError {
        return ResponseState.StateError(
            ErrorModel.ApiError(
                TextWrapper.StringText(exception.message!!),
                Constants.DEFAULT_ERROR_CODE
            )
        )
    }
}