package dev.awd.auth.domain.models

data class User(
    val username: String,
    val email: String,
    val gender: String? = "Male",
    val avatarUrl: String?
)
