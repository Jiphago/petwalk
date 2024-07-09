package com.example.petwalk.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petwalk.R
import com.example.petwalk.data.ChatItem

class ChatItemAdapter(private val items: List<ChatItem>) : RecyclerView.Adapter<ChatItemAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileImage: ImageView = view.findViewById(R.id.profile_image)
        val name: TextView = view.findViewById(R.id.profile_name)
        val message: TextView = view.findViewById(R.id.profile_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.profileImage.setImageResource(item.profileImageResId)
        holder.name.text = item.name
        holder.message.text = item.message
    }

    override fun getItemCount() = items.size
}