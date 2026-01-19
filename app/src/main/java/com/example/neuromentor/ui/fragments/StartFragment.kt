package com.example.neuromentor.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neuromentor.databinding.FragmentStartBinding
import com.example.neuromentor.viewmodels.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<StartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userId.observe(viewLifecycleOwner) { userId ->
          handleNavigation(userId)
        }
    }

    private fun handleNavigation(userId: Int?) {
        if (userId != null) {
            val action = StartFragmentDirections.actionStartFragmentToDialogFragment(userId)
            findNavController().navigate(action)
        } else {
            val action = StartFragmentDirections.actionStartFragmentToNameFragment()
            findNavController().navigate(action)
        }
    }

}

