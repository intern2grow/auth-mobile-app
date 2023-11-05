package dev.awd.auth.presentation

import dev.awd.auth.domain.models.User

sealed interface AuthUiState {
    data object Loading : AuthUiState

    data class Success(
        val user: User
    ) : AuthUiState

    data class Failure(
        val error: String
    ) : AuthUiState
}