package com.example.neuromentor.domain.models

import com.google.gson.annotations.SerializedName


data class UserRequest(
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String?,
    @SerializedName("age") val age: Int?
)

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("age") val age: Int?
)

data class ChatRequest(
    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("session_id")
    val sessionId: Int?,

    @SerializedName("message")
    val message: String
)

data class AIResponse(
    @SerializedName("answer")
    val answer: String,

    @SerializedName("session_id")
    val sessionId: Int
)

data class MessageResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("sender") val sender: String,
    @SerializedName("message_text") val messageText: String,
    @SerializedName("created_at") val createdAt: String
)

data class HistoryResponse(
    @SerializedName("session_id") val sessionId: Int,
    @SerializedName("messages") val messages: List<MessageResponse>
)