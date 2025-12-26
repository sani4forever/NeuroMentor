package com.example.neuromentor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neuromentor.domain.repository.ChatRepository
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

    private var currentSessionId: Int = 0

    fun sendMessageToApi(userText: String, userId: Int) {
        if (userText.isBlank()) return

        viewModelScope.launch {
            _messages.value += UserChatMessage(text = userText)

            val loadingMsg = NeuroChatMessage(text = null)
            _messages.value += loadingMsg

            try {
                val response = chatRepository.getAiResponse(
                    userId = userId,
                    sessionId = currentSessionId,
                    message = userText
                )

                _messages.value = _messages.value.filter { it != loadingMsg }

                response?.let {
                    currentSessionId = it.sessionId
                    _messages.value += NeuroChatMessage(text = it.answer)
                }
            } catch (_: Exception) {
                _messages.value = _messages.value.filter { it != loadingMsg }
                _messages.value += NeuroChatMessage(text = "Ошибка связи с сервером")
            }
        }
    }

    fun loadChatHistory(userId: Int) {
        viewModelScope.launch {
            try {
                val history = chatRepository.getHistory(userId, null)
                history?.let { res ->
                    currentSessionId = res.sessionId

                    val mappedMessages = res.messages.map { msg ->
                        if (msg.sender == "user") {
                            UserChatMessage(text = msg.messageText)
                        } else {
                            NeuroChatMessage(text = msg.messageText)
                        }
                    }
                    _messages.value = mappedMessages
                }
            } catch (_: Exception) {

            }
        }
    }
}