package dev.awd.auth.presentation.register

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
import com.google.android.material.snackbar.Snackbar
import dev.awd.auth.AuthApplication
import dev.awd.auth.R
import dev.awd.auth.databinding.FragmentRegisterBinding
import dev.awd.auth.utils.Response
import dev.awd.auth.utils.invisibleIf
import dev.awd.auth.utils.text
import dev.awd.auth.utils.viewModelFactory
import dev.awd.auth.utils.visibleIf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterFragment : Fragment() {


    private val viewModel by viewModels<RegisterViewModel>(
        ownerProducer = { this },
        factoryProducer = {
            viewModelFactory {
                AuthApplication.appModule.run {
                    RegisterViewModel(
                        registerUseCase,
                        validateEmailUseCase,
                        validatePasswordUseCase
                    )
                }
            }
        })
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerBtn.setOnClickListener {
            val email = binding.emailField.emailIl.text
            val username = binding.usernameField.usernameIl.text
            val password = binding.passwordField.passwordIl.text
            viewModel.register(email, username, password)
            listenToRegisterStatus()
        }
    }

    private fun listenToRegisterStatus() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {

                viewModel.registerState.collectLatest { state ->

                    binding.progressBar visibleIf (state is Response.Loading)
                    binding.registerBtn invisibleIf (state is Response.Loading)

                    withContext(Dispatchers.Main) {
                        when (state) {
                            is Response.Failure -> {
                                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT)
                                    .show()
                            }

                            is Response.Success<*> -> {
                                navToProfile()
                            }

                            else -> {}

                        }
                    }
                }
            }
        }
    }


    private fun navToProfile() {
        Navigation.findNavController(binding.root).navigate(
            R.id.action_registerFragment_to_profileFragment
        )
    }
}