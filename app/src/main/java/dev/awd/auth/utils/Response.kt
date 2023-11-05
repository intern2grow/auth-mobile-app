package dev.awd.auth.utils

sealed class Response {

    class Success<T>(val data: T) : Response()
    class Failure(val error: String) : Response()
    data object Loading : Response()
}
