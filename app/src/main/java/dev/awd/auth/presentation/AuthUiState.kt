package dev.awd.auth.presentation

sealed interface AuthUiState {
    data object Loading : AuthUiState

    data class Success<T>(
        val data: T
    ) : AuthUiState

    data class Failure(
        val error: String
    ) : AuthUiState
}