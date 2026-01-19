package com.example.neuromentor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.neuromentor.domain.datastore.UserPreferencesRepository

class StartViewModel(repository: UserPreferencesRepository): ViewModel() {
    val userId = repository.userIdFlow.asLiveData()
}