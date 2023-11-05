package dev.awd.auth.data.repository

import android.util.Log
import dev.awd.auth.data.remote.AuthClient
import dev.awd.auth.data.remote.UserRequest
import dev.awd.auth.domain.repository.AuthRepository
import dev.awd.auth.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class AuthRepoImpl(private val authClient: AuthClient) : AuthRepository {
    override suspend fun login(username: String, password: String) =
        flowOf(
            try {
                val response = authClient.login(UserRequest(email = username, password = password))
                Log.d("Auth Repo", "Login Succeeded: $response")
                Response.Success(
                    response
                )
            } catch (e: Throwable) {
                Log.d("Auth Repo", "Login failed: ${e.message}")
                Response.Failure(e.message ?: "unknown error")
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
                Response.Success(response)

            } catch (e: Throwable) {
                e.printStackTrace()
                Log.e("Auth Repo", "register failed for: ${e.message}")
                Response.Failure(e.message ?: "unknown error")
            }
        ).flowOn(Dispatchers.IO)

    override suspend fun logOut() =
        flowOf(
            try {
                Response.Success(
                    authClient.logOut()
                )
            } catch (e: Throwable) {
                Response.Failure(e.message ?: "unknown error")
            }
        ).flowOn(Dispatchers.IO)

}
