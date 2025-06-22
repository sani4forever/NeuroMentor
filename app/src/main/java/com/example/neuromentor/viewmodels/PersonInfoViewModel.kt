package com.example.neuromentor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonInfoViewModel : ViewModel() {
    private val _age = MutableStateFlow<Int?>(null)
    val age: StateFlow<Int?> = _age

    private val _name = MutableStateFlow<String?>(null)
    val name: StateFlow<String?> = _name

    fun saveName(name: String?) {
        _name.value = name
    }

    fun saveAge(age: Int) {
        viewModelScope.launch {
            if (age in 0..99) {
                _age.value = age
            }
        }
    }

}