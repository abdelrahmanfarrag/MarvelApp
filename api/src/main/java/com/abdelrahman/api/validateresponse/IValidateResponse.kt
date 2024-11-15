package com.abdelrahman.api.validateresponse

import com.abdelrahman.api.models.ResponseState
import retrofit2.Response

interface IValidateResponse {
    suspend fun <T> validateApiResponse(response: Response<T>):ResponseState<T>

}