package dev.awd.auth.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dev.awd.auth.AuthApplication
import dev.awd.auth.R
import dev.awd.auth.databinding.FragmentLoginBinding
import dev.awd.auth.domain.models.User
import dev.awd.auth.presentation.AuthUiState
import dev.awd.auth.utils.invisibleIf
import dev.awd.auth.utils.text
import dev.awd.auth.utils.viewModelFactory
import dev.awd.auth.utils.visibleIf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels(ownerProducer = { this }) {
        viewModelFactory {
            AuthApplication.appModule.run {
                LoginViewModel(
                    loginUseCase,
                    setUserDataUseCase,
                    getUserDataUseCase
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToLoginStatus()

        binding.loginBtn.setOnClickListener {
            login()
        }
        binding.registerTxtBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login() {
        val username = binding.usernameField.usernameIl.text
        val password = binding.passwordField.passwordIl.text

        viewModel.login(username, password, binding.rememberMeCheckbox.isChecked)
    }

    private fun listenToLoginStatus() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loginState.collectLatest { state ->
                    binding.progressBar visibleIf (state is AuthUiState.Loading)
                    binding.loginBtn invisibleIf (state is AuthUiState.Loading)

                    withContext(Dispatchers.Main) {
                        when (state) {
                            is AuthUiState.Failure -> {
                                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT)
                                    .show()
                            }

                            is AuthUiState.Success<*> -> {
                                navToProfile(userData = state.data as User)
                            }

                            else -> {}

                        }
                    }
                }
            }
        }
    }

    private fun navToProfile(userData: User) {

        LoginFragmentDirections.actionLoginFragmentToProfileFragment(userArg = userData).run {
            findNavController().navigate(this)
        }
    }
}