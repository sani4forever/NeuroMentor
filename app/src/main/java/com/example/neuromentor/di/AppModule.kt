package com.example.neuromentor.di

import com.example.neuromentor.api.ChatApi
import com.example.neuromentor.data.ChatRepository
import com.example.neuromentor.viewmodels.ChatViewModel
import com.example.neuromentor.viewmodels.PersonInfoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:8000/api/v1/"


val appModule = module {

    viewModel { PersonInfoViewModel(get()) }

    viewModel { ChatViewModel(get()) }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ChatApi> { get<Retrofit>().create(ChatApi::class.java) }

    single { ChatRepository(get()) }
}
