package com.example.neuromentor.di

import com.example.neuromentor.viewmodels.PersonInfoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { PersonInfoViewModel() }

}
