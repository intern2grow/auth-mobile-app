package dev.awd.auth.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.awd.auth.domain.usecase.RegisterUseCase
import dev.awd.auth.domain.validationusecases.ValidateEmailUseCase
import dev.awd.auth.domain.validationusecases.ValidatePasswordUseCase
import dev.awd.auth.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterViewModel(
    val registerUseCase: RegisterUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {
    companion object {
        private const val TAG = "RegisterViewModel"
    }


    var registerState: MutableStateFlow<Response> = MutableStateFlow(Response.Loading)
        private set

    fun register(email: String, username: String, password: String) =
        viewModelScope.launch {
            if (isCredentialsValid(email, password)) {
                registerUseCase(email, username, password).catch {
                    registerState.value = Response.Failure(it.message!!)
                }.collectLatest {
                    Log.d(TAG, "register: $it")
                    registerState.value = Response.Success(it)
                }
            } else {
                registerState.value = Response.Failure("Invalid Credentials")
                Log.w(TAG, "register: Invalid Credentials")
            }
        }


    private fun isCredentialsValid(email: String, password: String) =
        validateEmailUseCase(email) && validatePasswordUseCase(password)
}