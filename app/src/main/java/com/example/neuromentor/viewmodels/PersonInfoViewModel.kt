package com.example.neuromentor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neuromentor.models.Gender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonInfoViewModel : ViewModel() {
    private val _age = MutableStateFlow<Int?>(null)
    val age: StateFlow<Int?> = _age

    private val _name = MutableStateFlow<String?>(null)
    val name: StateFlow<String?> = _name

    private val _gender = MutableStateFlow<Gender?>(null)
    val gender: StateFlow<String?> = _name

    fun saveName(name: String?) {
        _name.value = name
    }

    fun saveAge(age: Int) {
        viewModelScope.launch {
            _age.value = age
        }
    }
    fun saveGender(gender: Gender?) {
        viewModelScope.launch {
            _gender.value = gender
        }
    }
}