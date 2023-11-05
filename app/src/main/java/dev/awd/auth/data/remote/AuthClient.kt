package dev.awd.auth.data.remote

import dev.awd.auth.data.responses.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthClient {
    @POST("auth/login")
    suspend fun login(@Body userRequest: UserRequest): UserResponse

    @POST("users/add")
    suspend fun register(@Body body: UserRequest): UserResponse

    @POST("logout")
    suspend fun logOut(): UserResponse

}