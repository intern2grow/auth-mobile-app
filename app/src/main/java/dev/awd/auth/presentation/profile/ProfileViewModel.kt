package dev.awd.auth.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.awd.auth.data.ApiResponse
import dev.awd.auth.domain.usecase.ClearUserDataUseCase
import dev.awd.auth.domain.usecase.LogoutUseCase
import dev.awd.auth.presentation.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(
    val logoutUseCase: LogoutUseCase,
    val clearUserDataUseCase: ClearUserDataUseCase
) : ViewModel() {

    var logoutUiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.Loading)
        private set

    fun logOut() = viewModelScope.launch {
        viewModelScope.launch {
            logoutUseCase().collectLatest { response ->
                logoutUiState.value = when (response) {
                    is ApiResponse.Success<*> -> {
                        clearUserDataUseCase()
                        AuthUiState.Success(response.data)
                    }

                    is ApiResponse.Failure -> AuthUiState.Failure(response.error)
                    is ApiResponse.Loading -> AuthUiState.Loading
                }
            }
        }
    }


}