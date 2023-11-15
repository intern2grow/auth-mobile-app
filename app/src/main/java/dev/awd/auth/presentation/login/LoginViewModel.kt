package dev.awd.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.awd.auth.data.ApiResponse
import dev.awd.auth.domain.models.User
import dev.awd.auth.domain.usecase.GetUserDataUseCase
import dev.awd.auth.domain.usecase.LoginUseCase
import dev.awd.auth.domain.usecase.SetUserDataUseCase
import dev.awd.auth.presentation.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(
    val loginUseCase: LoginUseCase,
    val setUserDataUseCase: SetUserDataUseCase,
    val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    companion object {

        private const val TAG = "LoginViewModel"
    }

    var loginState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.Unknown)
        private set

    init {
        viewModelScope.launch {
            getUserDataUseCase().collectLatest { user ->
                if (user.username.isNotBlank()) {
                    loginState.value = AuthUiState.Success(user)
                }
            }
        }
    }

    fun login(username: String, password: String, rememberMe: Boolean) = viewModelScope.launch {
        if (username.isBlank() or password.isBlank()) {
            loginState.value = AuthUiState.Failure("Fields Can't be empty")
        } else
            loginUseCase(username, password).collectLatest { response ->
                loginState.value =
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
    }

}