package dev.awd.auth.domain.usecase

import dev.awd.auth.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, username: String, password: String) =
        authRepository.register(
            email = email,
            username = username,
            password = password
        )
}