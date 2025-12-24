package com.example.neuromentor.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.neuromentor.R
import com.example.neuromentor.models.ChatMessage
import com.example.neuromentor.models.NeuroChatMessage
import com.example.neuromentor.models.UserChatMessage


class ChatAdapter : ListAdapter<ChatMessage, RecyclerView.ViewHolder>(DiffCallback()) {

    companion object {
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_NEURO = 2
        private const val VIEW_TYPE_LOADING = 3
    }

    class UserMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.text_message_body)
    }

    class NeuroMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.text_message_body)
    }

    class MessageLoading(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is UserChatMessage -> VIEW_TYPE_USER
            is NeuroChatMessage -> item.text?.let { VIEW_TYPE_NEURO } ?: VIEW_TYPE_LOADING
            else -> VIEW_TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_user, parent, false)
                UserMessageViewHolder(view)
            }

            VIEW_TYPE_NEURO -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_neuro, parent, false)
                NeuroMessageViewHolder(view)
            }

            VIEW_TYPE_LOADING -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_mesage_loading, parent, false)
                MessageLoading(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is UserMessageViewHolder -> holder.messageText.text = (item as UserChatMessage).text
            is NeuroMessageViewHolder -> holder.messageText.text = (item as NeuroChatMessage).text
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ChatMessage>() {
        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem === newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }
    }
}
