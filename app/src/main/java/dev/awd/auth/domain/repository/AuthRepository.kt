package dev.awd.auth.domain.repository

import dev.awd.auth.data.ApiResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<ApiResponse>

    suspend fun register(email: String, username: String, password: String): Flow<ApiResponse>

    suspend fun logOut(): Flow<ApiResponse>
}