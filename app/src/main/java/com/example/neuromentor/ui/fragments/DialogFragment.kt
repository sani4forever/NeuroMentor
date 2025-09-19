package com.example.neuromentor.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neuromentor.databinding.FragmentDialogBinding
import com.example.neuromentor.ui.recyclerview.ChatAdapter
import com.example.neuromentor.ui.recyclerview.ChatMessage

class DialogFragment : Fragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!
    private val messages = mutableListOf(
        ChatMessage(
            text = "Привет. Я постоянно тревожусь из-за работы, не могу расслабиться.",
            isFromUser = true
        ), ChatMessage(
            text = "Здравствуйте. Понимаю, как это изматывает. Расскажите, что именно вызывает наибольшее беспокойство?",
            isFromUser = false
        ), ChatMessage(
            text = "Начальник постоянно недоволен, боюсь, что меня уволят. И дедлайны давят.",
            isFromUser = true
        ), ChatMessage(
            text = "Страх потерять работу — это сильный стресс. Давайте попробуем одно упражнение. Сосредоточьтесь на дыхании. Вдох на четыре счета, задержка на четыре, выдох на четыре. Повторите три раза.",
            isFromUser = false
        ), ChatMessage(
            text = "Попробовал. Стало немного легче, спасибо.", isFromUser = true
        ), ChatMessage(
            text = "Отлично. Это простое упражнение помогает вернуться в настоящий момент и снизить уровень тревоги. Вы можете использовать его в любое время, когда почувствуете напряжение.",
            isFromUser = false
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        val adapter = ChatAdapter(messages)
        binding.recyclerViewChat.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}