package dev.awd.auth.domain.usecase

import dev.awd.auth.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.logOut()
}