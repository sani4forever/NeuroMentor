package com.example.neuromentor.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neuromentor.R

class ChatAdapter(private val messages: MutableList<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_NEURO = 2
    }

    inner class UserMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.text_message_body)
    }

    inner class NeuroMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.text_message_body)
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isFromUser) VIEW_TYPE_USER else VIEW_TYPE_NEURO

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

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_USER -> {
                val message = messages[position]
                (holder as UserMessageViewHolder).messageText.text = message.text
            }

            VIEW_TYPE_NEURO -> {
                val message = messages[position]
                (holder as NeuroMessageViewHolder).messageText.text = message.text
            }
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }
}

// Класс данных для сообщения
data class ChatMessage(
    val text: String, val isFromUser: Boolean
)