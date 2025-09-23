package com.example.neuromentor.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.neuromentor.databinding.FragmentChatBinding
import com.example.neuromentor.models.UserChatMessage
import com.example.neuromentor.ui.recyclerview.ChatAdapter
import com.example.neuromentor.viewmodels.ChatViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ChatAdapter

    private val viewModel by viewModel<ChatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        adapter = ChatAdapter()
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerViewChat.adapter = adapter
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.messages.collect { messages ->
                        adapter.submitList(messages)
                        viewModel.messages.value?.lastIndex?.let {
                            binding.recyclerViewChat.smoothScrollToPosition(it)
                        }
                    }
                }
            }

            buttonSend.setOnClickListener(onSendClickListener)
        }

    }

    private val onSendClickListener: OnClickListener = OnClickListener {
        with(binding.editTextMessage.text) {
            if (this.isNotBlank()) {
                viewModel.addMessage(UserChatMessage(text = this.toString()))
            }
            clear()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}