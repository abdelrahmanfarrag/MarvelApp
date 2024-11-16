package com.abdelrahman.api.mappers

import com.abdelrahman.api.models.ResponseState
import com.abdelrahman.shared_domain.models.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T, R> ResponseState<T>.mapToDataState(map: (T?) -> R): Flow<DataState<R>> {
    val resultState = when (this) {
        is ResponseState.StateSuccess -> DataState.Success(map(this.data))
        is ResponseState.StateError -> DataState.Error(this.errorModel)
    }
    return flow {
        emit(resultState)
    }
}