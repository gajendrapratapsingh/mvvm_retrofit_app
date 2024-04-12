package com.info.mdw.api

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String?) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}