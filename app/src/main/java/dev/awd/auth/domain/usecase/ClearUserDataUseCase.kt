package dev.awd.auth.domain.usecase

import dev.awd.auth.domain.repository.UserPrefsRepository

class ClearUserDataUseCase(private val userPrefsRepository: UserPrefsRepository) {
    suspend operator fun invoke() = userPrefsRepository.clearUserData()
}