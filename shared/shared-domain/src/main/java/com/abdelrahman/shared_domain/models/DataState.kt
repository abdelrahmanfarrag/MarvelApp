package com.abdelrahman.shared_domain.models

sealed class DataState<T>(val result: T?) {
    data class Success<T>(val data: T?) : DataState<T>(data)
    data class Error<T>(val errorModel: ErrorModel?) : DataState<T>(null)
}