package dev.awd.auth.data

sealed class ApiResponse {

    class Success<T>(val data: T) : ApiResponse()
    class Failure(val error: String) : ApiResponse()
    data object Loading : ApiResponse()
}
