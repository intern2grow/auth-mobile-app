package dev.awd.auth.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.awd.auth.data.ApiResponse
import dev.awd.auth.domain.models.User
import dev.awd.auth.domain.usecase.RegisterUseCase
import dev.awd.auth.domain.usecase.SetUserDataUseCase
import dev.awd.auth.domain.validationusecases.ValidateEmailUseCase
import dev.awd.auth.domain.validationusecases.ValidatePasswordUseCase
import dev.awd.auth.presentation.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterViewModel(
    val registerUseCase: RegisterUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val setUserDataUseCase: SetUserDataUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "RegisterViewModel"
    }


    var registerUiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.Loading)
        private set

    fun register(email: String, username: String, password: String, rememberMe: Boolean) =
        viewModelScope.launch {
            if (isCredentialsValid(email, password)) {
                registerUseCase(email, username, password).collectLatest { response ->
                    registerUiState.value =
                        when (response) {
                            is ApiResponse.Success<*> -> {
                                val user = response.data as User
                                if (rememberMe) setUserDataUseCase(user)
                                AuthUiState.Success(user)

                            }

                            is ApiResponse.Failure -> AuthUiState.Failure(response.error)
                            is ApiResponse.Loading -> AuthUiState.Loading
                        }

                }
            } else {
                registerUiState.value = AuthUiState.Failure("Invalid Credentials")
                Log.w(TAG, "register: Invalid Credentials")
            }
        }


    private fun isCredentialsValid(email: String, password: String) =
        validateEmailUseCase(email) && validatePasswordUseCase(password)
}