package com.example.neuromentor.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neuromentor.databinding.FragmentGenderBinding
import com.example.neuromentor.models.Gender
import com.example.neuromentor.viewmodels.PersonInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenderFragment : Fragment() {

    private var _binding: FragmentGenderBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<PersonInfoViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            listOf(buttonMale, buttonFemale, buttonOther).forEach {
                it.setOnClickListener(onClickListener)
            }
        }
    }

    private val onClickListener = OnClickListener { view ->
        saveGender(view)
        navigateToAgeFragment()
    }

    private fun saveGender(view: View) {
        val genderInfo = with(binding) {
            when (view) {
                buttonMale -> Gender.MALE
                buttonFemale -> Gender.FEMALE
                else -> Gender.OTHER
            }
        }
        viewModel.saveGender(genderInfo)
    }

    private fun navigateToAgeFragment() {
        val action = GenderFragmentDirections.actionGenderFragmentToAgeFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
