package com.example.neuromentor.models

abstract class ChatMessage

data class UserChatMessage(
    val text: String
) : ChatMessage()

data class NeuroChatMessage(
    val text: String? = null
) : ChatMessage()
