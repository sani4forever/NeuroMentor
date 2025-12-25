package com.example.neuromentor.domain.repository

import android.util.Log
import com.example.neuromentor.domain.api.ChatApi
import com.example.neuromentor.domain.models.AIResponse
import com.example.neuromentor.domain.models.ChatRequest
import com.example.neuromentor.domain.models.UserRequest
import com.example.neuromentor.domain.models.UserResponse


class ChatRepository(private val api: ChatApi) {

    suspend fun registerUser(name: String, gender: String?, age: Int?): UserResponse? {
        val request = UserRequest(name, gender, age)
        val response = api.postUser(request)

        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getAiResponse(userId: Int, sessionId: Int, message: String): AIResponse? {
        val request = ChatRequest(userId, sessionId, message)
        val response = api.sendMessage(request)

        return response.body()
    }
}