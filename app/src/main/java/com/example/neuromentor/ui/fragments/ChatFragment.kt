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
import androidx.navigation.fragment.navArgs
import com.example.neuromentor.R
import com.example.neuromentor.databinding.FragmentChatBinding
import com.example.neuromentor.ui.recyclerview.ChatAdapter
import com.example.neuromentor.viewmodels.ChatViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ChatAdapter

    private val viewModel by viewModel<ChatViewModel>()

    private val args: ChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        adapter = ChatAdapter()
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel.loadChatHistory(args.userID)
            recyclerViewChat.adapter = adapter
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.messages.collect { messages ->
                        adapter.submitList(messages) {
                            if (messages.isNotEmpty()) {
                                binding.recyclerViewChat.smoothScrollToPosition(messages.lastIndex)
                            }
                        }

                        val isEmpty = messages.isEmpty()
                        binding.containerWelcome.visibility = if (isEmpty) View.VISIBLE else View.GONE
                        binding.scrollSuggestions.visibility = if (isEmpty) View.VISIBLE else View.GONE
                        binding.recyclerViewChat.visibility = if (isEmpty) View.GONE else View.VISIBLE

                        if (isEmpty) setupSuggestions(listOf("Чувствую тревогу без причины", "У меня нет сил что-либо делать", "Скажи мне что-то поддерживающее","Чувствую себя одиноко"))
                    }
                }
            }
            buttonSend.setOnClickListener(onSendClickListener)
        }
    }

    private fun setupSuggestions(suggestions: List<String>) {
        binding.chipGroupSuggestions.removeAllViews()

        suggestions.forEach { text ->
            val chip = layoutInflater.inflate(
                R.layout.item_suggestion_chip,
                binding.chipGroupSuggestions,
                false
            ) as com.google.android.material.chip.Chip

            chip.apply {
                this.text = text
                setOnClickListener {
                    viewModel.sendMessageToApi(text, args.userID)
                    binding.scrollSuggestions.animate().alpha(0f).withEndAction {
                        binding.scrollSuggestions.visibility = View.GONE
                        binding.scrollSuggestions.alpha = 1f
                    }
                }
            }
            binding.chipGroupSuggestions.addView(chip)
        }
    }

    private val onSendClickListener: OnClickListener = OnClickListener {
        val text = binding.editTextMessage.text.toString()
        viewModel.sendMessageToApi(text, args.userID)
        binding.editTextMessage.text.clear()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}