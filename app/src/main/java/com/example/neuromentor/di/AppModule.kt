package com.example.neuromentor.di

import com.example.neuromentor.domain.api.ChatApi
import com.example.neuromentor.domain.datastore.UserPreferencesRepository
import com.example.neuromentor.domain.repository.ChatRepository
import com.example.neuromentor.viewmodels.ChatViewModel
import com.example.neuromentor.viewmodels.PersonInfoViewModel
import com.example.neuromentor.viewmodels.StartViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://10.0.2.2:8000/api/v1/"


val appModule = module {

    viewModel { PersonInfoViewModel(get(), get()) }

    viewModel { ChatViewModel(get()) }

    viewModel { StartViewModel(get()) }

    single {
        OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ChatApi> { get<Retrofit>().create(ChatApi::class.java) }

    single { ChatRepository(get()) }

    single { UserPreferencesRepository(get()) }
}
