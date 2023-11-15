package dev.awd.auth.domain.usecase

import dev.awd.auth.domain.repository.UserPrefsRepository

class GetUserDataUseCase(
    private val userPrefsRepository: UserPrefsRepository
) {
    operator fun invoke() = userPrefsRepository.getUserData()
}