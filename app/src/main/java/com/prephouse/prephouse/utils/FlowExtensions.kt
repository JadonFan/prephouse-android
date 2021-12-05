package com.prephouse.prephouse.utils

import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response

suspend fun <T> FlowCollector<T>.emitApiResponse(
    response: Response<T>,
    errorMessage: String? = null
) {
    val body = response.body()
    if (response.isSuccessful && body != null) {
        emit(body)
    } else {
        error(errorMessage ?: response.message())
    }
}

suspend fun <T> FlowCollector<T?>.emitNullableApiResponse(
    response: Response<T?>,
    errorMessage: String? = null
) {
    if (response.isSuccessful) {
        emit(response.body())
    } else {
        error(errorMessage ?: response.message())
    }
}
