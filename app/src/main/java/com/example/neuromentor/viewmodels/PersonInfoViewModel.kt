package com.example.neuromentor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neuromentor.data.ChatRepository
import com.example.neuromentor.models.Gender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonInfoViewModel(private val chatRepository: ChatRepository) : ViewModel() {
    private val _age = MutableStateFlow<Int?>(null)
    val age: StateFlow<Int?> = _age

    private val _name = MutableStateFlow<String?>(null)
    val name: StateFlow<String?> = _name

    private val _gender = MutableStateFlow<Gender?>(null)
    val gender: StateFlow<Gender?> = _gender

    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId

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

    fun registerAndGetId(onSuccess: (Int) -> Unit) {
        val currentName = name.value ?: ""

        if (currentName.isBlank()) {
            android.util.Log.e("API_CHECK", "Имя пустое, сервер вернет 422")
        }

        viewModelScope.launch {
            try {
                val response = chatRepository.registerUser(
                    name = currentName,
                    gender = gender.value?.toString(),
                    age = age.value
                )

                response?.let {
                    onSuccess(it.id)
                }
            } catch (e: Exception) {
                android.util.Log.e("REG_ERROR", "Registration failed", e)
            }
        }
    }
}