package com.example.neuromentor.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neuromentor.R
import com.example.neuromentor.databinding.FragmentNameBinding
import com.example.neuromentor.viewmodels.PersonInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NameFragment : Fragment() {

    private var _binding: FragmentNameBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<PersonInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            continueButton.text = getString(R.string.skip_btn)
            nameEditText.addTextChangedListener(textWatcher)
            continueButton.setOnClickListener(onClickListener)
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            binding.continueButton.text = if (s.isNullOrEmpty()) {
                getString(R.string.skip_btn)
            } else {
                getString(R.string.continue_btn)
            }
        }
    }

    private val onClickListener = OnClickListener {
        val name = binding.nameEditText.text.toString()
        viewModel.saveName(name)
        navigateToGender()
    }

    private fun navigateToGender() {
        findNavController().navigate(NameFragmentDirections.actionNameFragmentToGenderFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}