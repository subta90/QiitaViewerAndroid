package com.example.qiitaviewerandroid.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.databinding.ArticleItemViewBinding

class ArticleListItemAdapter(val clickListener: ArticleListItemListner) :
    PagingDataAdapter<ArticleOverview, ArticleListItemAdapter.ArticleOverViewViewHolder>(
        DiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleOverViewViewHolder {
        return ArticleOverViewViewHolder(
            ArticleItemViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ArticleOverViewViewHolder, position: Int) {
        var articleOverview = getItem(position)
        holder.bind(articleOverview, clickListener)
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
        fun bind(overview: ArticleOverview?, clickListener: ArticleListItemListner) {
            binding.property = overview
            binding.clickListener = clickListener
            val adapter = TagsAdapter()
            binding.articleItemTags.adapter = adapter
            binding.executePendingBindings()
        }
    }

    class ArticleListItemListner(val clickListener: (overview: ArticleOverview) -> Unit) {
        fun onClick(overview: ArticleOverview) = clickListener(overview)
    }
}

