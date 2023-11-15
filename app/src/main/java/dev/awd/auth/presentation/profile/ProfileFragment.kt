package dev.awd.auth.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dev.awd.auth.AuthApplication
import dev.awd.auth.databinding.FragmentProfileBinding
import dev.awd.auth.presentation.AuthUiState
import dev.awd.auth.utils.invisibleIf
import dev.awd.auth.utils.viewModelFactory
import dev.awd.auth.utils.visibleIf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {

    private val viewModel by viewModels<ProfileViewModel>(ownerProducer = { this }) {
        viewModelFactory {
            ProfileViewModel(
                AuthApplication.appModule.logoutUseCase,
                AuthApplication.appModule.clearUserDataUseCase
            )
        }
    }
    private val args: ProfileFragmentArgs by navArgs()

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = args.userArg
        binding.logoutBtn.setOnClickListener {
            viewModel.logOut()
            listenToLogOutStatus()
        }
    }

    private fun listenToLogOutStatus() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {

                viewModel.logoutUiState.collectLatest { state ->

                    binding.progressBar visibleIf (state is AuthUiState.Loading)
                    binding.logoutBtn invisibleIf (state is AuthUiState.Loading)

                    withContext(Dispatchers.Main) {
                        when (state) {
                            is AuthUiState.Failure -> {
                                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT)
                                    .show()
                            }

                            is AuthUiState.Success<*> -> {
                                navToLogin()
                            }

                            else -> {}

                        }
                    }
                }
            }
        }
    }

    private fun navToLogin() {
        ProfileFragmentDirections.actionProfileFragmentToLoginFragment().run {
            findNavController().navigate(this)
        }
    }
}