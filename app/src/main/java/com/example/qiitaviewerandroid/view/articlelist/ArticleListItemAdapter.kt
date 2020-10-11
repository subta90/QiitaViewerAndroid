package com.example.qiitaviewerandroid.view.articlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.databinding.ArticleItemViewBinding
import com.example.qiitaviewerandroid.view.common.TagsAdapter

class ArticleListItemAdapter(
    val itemClickListener: ArticleListItemListner,
    val tagClickListener: TagsAdapter.TagButtonItemListener
) :
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
        holder.bind(articleOverview, itemClickListener, tagClickListener)
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

    class ArticleOverViewViewHolder(private var binding: ArticleItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            overview: ArticleOverview?,
            itemClickListener: ArticleListItemListner,
            tagClickListener: TagsAdapter.TagButtonItemListener
        ) {
            binding.property = overview
            binding.clickListener = itemClickListener
            val adapter = TagsAdapter(tagClickListener)
            binding.articleItemTags.adapter = adapter
            binding.executePendingBindings()
        }
    }

    class ArticleListItemListner(val clickListener: (overview: ArticleOverview) -> Unit) {
        fun onClick(overview: ArticleOverview) = clickListener(overview)
    }
}

