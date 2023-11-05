package dev.awd.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.awd.auth.domain.models.User
import dev.awd.auth.domain.usecase.LoginUseCase
import dev.awd.auth.presentation.AuthUiState
import dev.awd.auth.data.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(
    val loginUseCase: LoginUseCase,
) : ViewModel() {

    companion object {

        private const val TAG = "LoginViewModel"
    }

    var loginState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.Loading)
        private set

    fun login(username: String, password: String) = viewModelScope.launch {
        if (username.isBlank() or password.isBlank()) {
            loginState.value = AuthUiState.Failure("Fields Can't be empty")
        } else
            loginUseCase(username, password).collectLatest { response ->
                loginState.value =
                    when (response) {
                        is ApiResponse.Success<*> -> AuthUiState.Success(response.data as User)
                        is ApiResponse.Failure -> AuthUiState.Failure(response.error)
                        is ApiResponse.Loading -> AuthUiState.Loading
                    }
            }
    }

}