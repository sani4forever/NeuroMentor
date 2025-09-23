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

    private val testData = mutableListOf(
        UserChatMessage(
            text = "Привет. Я постоянно тревожусь из-за работы, не могу расслабиться.",
        ), NeuroChatMessage(
            text = "Здравствуйте. Понимаю, как это изматывает. Расскажите, что именно вызывает наибольшее беспокойство?",
        ), UserChatMessage(
            text = "Начальник постоянно недоволен, боюсь, что меня уволят. И дедлайны давят.",
        ), NeuroChatMessage(
            text = "Страх потерять работу — это сильный стресс. Давайте попробуем одно упражнение. Сосредоточьтесь на дыхании. Вдох на четыре счета, задержка на четыре, выдох на четыре. Повторите три раза.",
        ), UserChatMessage(
            text = "Попробовал. Стало немного легче, спасибо.",
        ), NeuroChatMessage(
            text = "Отлично. Это простое упражнение помогает вернуться в настоящий момент и снизить уровень тревоги. Вы можете использовать его в любое время, когда почувствуете напряжение.",
        )
    )

    private val _messages = MutableStateFlow<List<ChatMessage>?>(testData)
    val messages: StateFlow<List<ChatMessage>?> = _messages

    fun addMessage(chatMessage: ChatMessage) {
        viewModelScope.launch {
            _messages.value = _messages.value?.plus(chatMessage)
        }

        if (chatMessage is UserChatMessage) getAnswer(chatMessage)
    }

    private fun getAnswer(userMessage: UserChatMessage) {
        addMessage(NeuroChatMessage())
        viewModelScope.launch {
            val answer = chatRepository.getAnswer(userMessage.text)
            viewModelScope.launch {
                _messages.value = _messages.value?.dropLast(1)
            }
            addMessage(answer)
        }
    }
}