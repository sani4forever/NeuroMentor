package com.example.neuromentor.data

import com.example.neuromentor.api.ChatApi
import com.example.neuromentor.models.AIResponse
import com.example.neuromentor.models.ChatRequest
import com.example.neuromentor.models.UserRequest
import com.example.neuromentor.models.UserResponse

class ChatRepository(private val api: ChatApi) {

    suspend fun registerUser(name: String, gender: String?, age: Int?): UserResponse? {
        val request = UserRequest(name, gender.toString(), age)
        val response = api.postUser(request)

        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getAiResponse(userId: Int, sessionId: Int, message: String): AIResponse? {
        val request = ChatRequest(userId, sessionId, message)
        val response = api.sendMessage(request)
        if (!response.isSuccessful) {
            android.util.Log.e("API_DEBUG", "422 Error: ${response.errorBody()?.string()}")
        }

        return response.body()
    }
}