package com.example.neuromentor.data

import com.example.neuromentor.models.ChatMessage
import com.example.neuromentor.models.NeuroChatMessage
import kotlinx.coroutines.delay

class ChatRepository {

    suspend fun getAnswer(userMessage: String): ChatMessage {
        delay(2000)
        return NeuroChatMessage(text = "neuro answer to: $userMessage")
        //TODO: api call
    }
}