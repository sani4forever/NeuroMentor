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
import com.example.neuromentor.databinding.FragmentAgeBinding
import com.example.neuromentor.viewmodels.PersonInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgeFragment : Fragment() {

    private var _binding: FragmentAgeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<PersonInfoViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            continueButton.text = getString(R.string.skip_btn)
            ageEditText.addTextChangedListener(textWatcher)
            continueButton.setOnClickListener(onButtonClickListener)
        }
    }

    private fun updateButtonText(text: String) {
        binding.continueButton.text = if (text.isNotEmpty()) {
            getString(R.string.continue_btn)
        } else {
            getString(R.string.skip_btn)
        }
    }


    private val onButtonClickListener: OnClickListener = OnClickListener {
        val ageText = binding.ageEditText.text.toString()
        if (ageText.isNotEmpty()) {
            val age = ageText.toInt()
            if (age in 0..99) {
                viewModel.saveAge(age)
                navigateToAgeFragment()
            } else {
                binding.ageEditText.error = getString(R.string.enter_your_age_0_99)
            }
        } else {
            navigateToAgeFragment()
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            updateButtonText(s.toString())
        }
    }

    private fun navigateToAgeFragment() {
        val action = AgeFragmentDirections.actionAgeFragmentToDialogFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}