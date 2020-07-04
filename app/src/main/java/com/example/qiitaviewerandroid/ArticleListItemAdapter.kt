package com.example.qiitaviewerandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qiitaviewerandroid.databinding.ArticleItemViewBinding

class ArticleListItemAdapter :
    PagingDataAdapter<ArticleOverview, ArticleListItemAdapter.ArticleOverViewViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleOverViewViewHolder {
        return ArticleOverViewViewHolder(ArticleItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArticleOverViewViewHolder, position: Int) {
        var articleOverview = getItem(position)
        holder.bind(articleOverview)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ArticleOverview>() {
        override fun areItemsTheSame(oldItem: ArticleOverview, newItem: ArticleOverview): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ArticleOverview,
            newItem: ArticleOverview
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ArticleOverViewViewHolder(private var binding: ArticleItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(overview: ArticleOverview?) {
            binding.property = overview
            val adapter = TagsAdapter()
            binding.articleItemTags.adapter = adapter
            binding.executePendingBindings()
        }
    }
}

