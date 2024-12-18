package com.app.nexttogo.utils.states

import com.app.nexttogo.utils.ApiError


sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Error(val apiError: ApiError) : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
}