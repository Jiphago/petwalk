package com.example.petwalk.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petwalk.R
import com.example.petwalk.data.HomeItem

class HomeItemAdapter(
    private val items: List<HomeItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<HomeItemAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val title: TextView = view.findViewById(R.id.text_title)
        val caption1: TextView = view.findViewById(R.id.text_caption1)
        val caption2: TextView = view.findViewById(R.id.text_caption2)
        val state: TextView = view.findViewById(R.id.text_state)
        val image: ImageView = view.findViewById(R.id.image_left)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.caption1.text = item.caption1
        holder.caption2.text = item.caption2
        holder.state.text = item.state
        holder.image.setImageResource(item.imageResId)
    }

    override fun getItemCount() = items.size
}
