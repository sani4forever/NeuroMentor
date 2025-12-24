package com.example.neuromentor.api

import com.example.neuromentor.models.AIResponse
import com.example.neuromentor.models.ChatRequest
import com.example.neuromentor.models.UserRequest
import com.example.neuromentor.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {
    @POST("user")
    suspend fun postUser(@Body userRequest: UserRequest): Response<UserResponse>

    @POST("chat")
    suspend fun sendMessage(@Body request: ChatRequest): Response<AIResponse>
}