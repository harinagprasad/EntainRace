package com.app.nexttogo.utils

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

data class ApiError(val code: Int, val message: String)

fun Throwable.parseError(): ApiError {
    return when (this) {
        is UnknownHostException, is SocketTimeoutException -> {
            ApiError(code = 12029, message = "Unknown host")
        }

        is JsonParseException, is JSONException -> {
            ApiError(code = 10001, message = "Unable to parse JSON")
        }

        is HttpException -> {
            val message = when (this.code()) {
                400 -> "Bad Request: The server cannot process the request due to a client error."
                401 -> "Unauthorized: The request requires user authentication."
                403 -> "Forbidden: The server understood the request, but refuses to authorize it."
                404 -> "Not Found: The requested resource could not be found."
                405 -> "Method Not Allowed: The method specified in the request is not allowed for the resource."
                500 -> "Internal Server Error: The server encountered an unexpected condition that prevented it from fulfilling the request."
                501 -> "Not Implemented: The server does not support the functionality required to fulfill the request."
                502 -> "Bad Gateway: The server, while acting as a gateway or proxy, received an invalid response from the upstream server it accessed in attempting to fulfill the request."
                503 -> "Service Unavailable: The server is currently unavailable."
                504 -> "Gateway Timeout: The server, while acting as a gateway or proxy, did not receive a timely response from the upstream server specified by the URI."
                else -> "Unknown HTTP Error: An unknown HTTP error occurred with status code ${this.code()}."
            }
            ApiError(code = this.code(), message = message)
        }

        is IOException -> {
            ApiError(code = 0, message = "Something went wrong" + this.message)
        }

        else -> {
            ApiError(code = 0, message = "No internet connection")
        }
    }
}