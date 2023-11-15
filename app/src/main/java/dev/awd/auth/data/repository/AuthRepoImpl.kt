package dev.awd.auth.data.repository

import android.util.Log
import dev.awd.auth.data.ApiResponse
import dev.awd.auth.data.mappers.toUser
import dev.awd.auth.data.remote.AuthClient
import dev.awd.auth.data.remote.UserRequest
import dev.awd.auth.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class AuthRepoImpl(private val authClient: AuthClient) : AuthRepository {
    override suspend fun login(username: String, password: String) =
        flowOf(
            try {
                val response = authClient.login(
                    UserRequest(
                        username = username,
                        password = password
                    )
                )
                Log.d("Auth Repo", "Login Succeeded: $response")
                ApiResponse.Success(response.toUser())
            } catch (e: HttpException) {
                val errorMessage =
                    if (e.code() == 400) "Only API users can login" else e.message
                ApiResponse.Failure(errorMessage ?: "unknown error")
            } catch (e: Throwable) {
                ApiResponse.Failure(e.message ?: "unknown error")
            }
        ).flowOn(Dispatchers.IO)

    override suspend fun register(email: String, username: String, password: String) =
        flowOf(
            try {
                val response =
                    authClient.register(
                        UserRequest(
                            email = email, username = username, password = password
                        )
                    )
                Log.d("Auth Repo", "register success: $response")
                ApiResponse.Success(response.toUser())

            } catch (e: Throwable) {
                Log.e("Auth Repo", "register failed for: ${e.message}")
                ApiResponse.Failure(e.message ?: "unknown error")
            }
        ).flowOn(Dispatchers.IO)

    override suspend fun logOut() =
        flowOf(
            try {
                ApiResponse.Success(
                    authClient.logOut()
                )
            } catch (e: Throwable) {
                ApiResponse.Failure(e.message ?: "unknown error")
            }
        ).flowOn(Dispatchers.IO)

}
