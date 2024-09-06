package com.example.socialmediadownloader.data.response

sealed class NetworkResponse<T>(
    val data: T? = null, val error: String? = null
) {
    class Idle<T>() : NetworkResponse<T>()
    class Loading<T>() : NetworkResponse<T>()
    class Failure<T>(error: String?) : NetworkResponse<T>(error = error, data = null)
    class Success<T>(data: T) : NetworkResponse<T>(data, error = null)
}

sealed class RequestTypes {
    data object Get : RequestTypes()
    data class Post(val body: Any?) : RequestTypes()
}
