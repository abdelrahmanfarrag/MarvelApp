package com.abdelrahman.api.models

import com.abdelrahman.shared_domain.models.ErrorModel

sealed class ResponseState<T> {

    data class StateSuccess<T>(
        val data: T?
    ) : ResponseState<T>()

    data class StateError(
        val errorModel: ErrorModel?
    ) : ResponseState<Any>()
}