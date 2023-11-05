package dev.awd.auth.domain.repository

import dev.awd.auth.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Flow<Response>

    suspend fun register(email: String, username: String, password: String): Flow<Response>

    suspend fun logOut(): Flow<Response>
}