package dev.awd.auth.domain.usecase

import dev.awd.auth.domain.repository.AuthRepository

class LoginUseCase(val authRepository: AuthRepository) {

    suspend operator fun invoke(username: String, password: String) =
        authRepository.login(username, password)
}