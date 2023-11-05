package dev.awd.auth.domain.validationusecases

class ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$".toRegex()
        return passwordPattern.matches(password)
    }

}