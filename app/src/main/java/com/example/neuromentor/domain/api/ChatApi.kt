package com.example.neuromentor.domain.api


import com.example.neuromentor.domain.models.AIResponse
import com.example.neuromentor.domain.models.ChatRequest
import com.example.neuromentor.domain.models.HistoryResponse
import com.example.neuromentor.domain.models.UserRequest
import com.example.neuromentor.domain.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatApi {
    @POST("user")
    suspend fun postUser(@Body userRequest: UserRequest): Response<UserResponse>

    @POST("chat")
    suspend fun sendMessage(@Body request: ChatRequest): Response<AIResponse>

    @GET("chat/history/{user_id}")
    suspend fun getChatHistory(
        @Path("user_id") userId: Int,
        @Query("session_id") sessionId: Int? = null
    ): Response<HistoryResponse>


}