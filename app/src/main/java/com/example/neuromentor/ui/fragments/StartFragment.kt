package com.example.neuromentor.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neuromentor.databinding.FragmentStartBinding
import com.example.neuromentor.viewmodels.PersonInfoViewModel
import com.example.neuromentor.viewmodels.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val startViewModel by viewModel<StartViewModel>()
    private val personViewModel by viewModel<PersonInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.retryButton.setOnClickListener {
            startViewModel.userId.value?.let { userId ->
                handleNavigation(userId)
            }
        }

        startViewModel.userId.observe(viewLifecycleOwner) { userId ->
            handleNavigation(userId)
        }
    }

    private fun handleNavigation(userId: Int?) {
        if (userId != null) {
            showLoading()

            personViewModel.registerAndGetId(
                onSuccess = { returnedUserId ->
                    hideLoading()
                    val action = StartFragmentDirections.actionStartFragmentToDialogFragment(returnedUserId)
                    findNavController().navigate(action)
                },
                onError = { errorMessage ->
                    showError(errorMessage)
                }
            )
        } else {
            val action = StartFragmentDirections.actionStartFragmentToNameFragment()
            findNavController().navigate(action)
        }
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
        binding.errorIcon.isVisible = false
        binding.errorTextView.isVisible = false
        binding.errorMessageTextView.isVisible = false
        binding.retryButton.isVisible = false
    }

    private fun showError(message: String) {
        binding.progressBar.isVisible = false
        binding.errorIcon.isVisible = true
        binding.errorTextView.isVisible = true
        binding.errorMessageTextView.isVisible = true
        binding.errorMessageTextView.text = message
        binding.retryButton.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}