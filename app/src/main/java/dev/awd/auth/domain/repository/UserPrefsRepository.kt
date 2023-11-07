package dev.awd.auth.domain.repository

import dev.awd.auth.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserPrefsRepository {
    suspend fun setUserData(user: User)
    fun getUserData(): Flow<User>
    suspend fun clearUserData()
}
