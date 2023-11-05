package dev.awd.auth.data.remote

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("email") var email: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("password") var password: String? = null,
)
