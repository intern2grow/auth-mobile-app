package dev.awd.auth.domain.usecase

import dev.awd.auth.domain.models.User
import dev.awd.auth.domain.repository.UserPrefsRepository

class SetUserDataUseCase(
    private val userPrefsRepository: UserPrefsRepository
) {
    suspend operator fun invoke(user: User) = userPrefsRepository.setUserData(user)
}