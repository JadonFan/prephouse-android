package com.prephouse.prephouse.datasources

sealed class DataResult<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T) : DataResult<T>(data)
    class Error<out T>(message: String?, data: T? = null) : DataResult<T>(data, message)
    class Loading<out T> : DataResult<T>()
}
