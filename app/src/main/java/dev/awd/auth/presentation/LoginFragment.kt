package dev.awd.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dev.awd.auth.R
import dev.awd.auth.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_profileFragment)
        }
        binding.registerTxtBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}