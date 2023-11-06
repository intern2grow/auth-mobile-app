package dev.awd.auth.data.mappers

import dev.awd.auth.data.responses.UserResponse
import dev.awd.auth.domain.models.User

fun UserResponse.toUser() = User(
    email = email ?: "email@provider.com",
    username = username ?: "username",
    gender = gender?.ifBlank { "Male" },
    avatarUrl = image,
    displayName = "$firstName $lastName"
)