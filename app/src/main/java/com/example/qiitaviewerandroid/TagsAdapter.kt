package com.example.qiitaviewerandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TagsAdapter: RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {

    var tags = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        return TagsViewHolder.from(parent)
    }

    override fun getItemCount() = tags.size

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        val tagName = tags[position]
        holder.bind(tagName)
    }

    class TagsViewHolder(val tagButton: Button): RecyclerView.ViewHolder(tagButton) {
        companion object {
            fun from(parent: ViewGroup): TagsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val tagButton = layoutInflater.inflate(R.layout.tag_button, parent, false) as Button
                return TagsViewHolder(tagButton)
            }
        }

        fun bind(tagName: String) {
            tagButton.text = tagName
        }

    }
}