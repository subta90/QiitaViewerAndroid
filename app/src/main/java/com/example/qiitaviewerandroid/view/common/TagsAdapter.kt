package com.example.qiitaviewerandroid.view.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.databinding.TagButtonBinding

class TagsAdapter: ListAdapter<ArticleOverview.Tag, TagsAdapter.TagButtonViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagButtonViewHolder {
        return TagButtonViewHolder(
            TagButtonBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: TagButtonViewHolder, position: Int) {
        var tag = getItem(position)
        holder.bind(tag)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ArticleOverview.Tag>() {
        override fun areItemsTheSame(
            oldItem: ArticleOverview.Tag,
            newItem: ArticleOverview.Tag
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ArticleOverview.Tag,
            newItem: ArticleOverview.Tag
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }

    class TagButtonViewHolder(private var binding: TagButtonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: ArticleOverview.Tag) {
            binding.tag = tag
            binding.executePendingBindings()
        }
    }
}
