package com.example.neuromentor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neuromentor.data.ChatRepository
import com.example.neuromentor.models.ChatMessage
import com.example.neuromentor.models.NeuroChatMessage
import com.example.neuromentor.models.UserChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private var currentUserId = 0
    private var currentSessionId: Int = 0

    fun sendMessageToApi(userText: String, userId: Int) {
        this.currentUserId = userId
        val userMsg = UserChatMessage(text = userText)
        _messages.value += userMsg

        viewModelScope.launch {
            try {
                val response = chatRepository.getAiResponse(
                    userId = currentUserId,
                    sessionId = currentSessionId,
                    message = userText
                )

                response?.let {
                    currentSessionId = it.sessionId // Сохраняем ID сессии для следующих сообщений
                    val aiMsg = NeuroChatMessage(text = it.answer)
                    _messages.value += aiMsg
                }
            } catch (e: Exception) {
                _messages.value += NeuroChatMessage(text = "Ошибка связи с сервером")
            }
        }
    }
}