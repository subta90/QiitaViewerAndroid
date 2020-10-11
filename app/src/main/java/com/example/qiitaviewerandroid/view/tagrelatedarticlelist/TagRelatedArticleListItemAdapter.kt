package com.example.qiitaviewerandroid.view.tagrelatedarticlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qiitaviewerandroid.databinding.ArticleItemViewBinding
import com.example.qiitaviewerandroid.databinding.TagRelatedArticleListHeaderViewBinding
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.view.articlelist.ArticleListItemAdapter
import com.example.qiitaviewerandroid.view.common.RecyclerType
import com.example.qiitaviewerandroid.view.common.TagsAdapter

class TagRelatedArticleListItemAdapter(
    val tag: ArticleOverview.Tag,
    val itemClickListener: ArticleListItemAdapter.ArticleListItemListner,
    val tagclickListener: TagsAdapter.TagButtonItemListener
) :
    PagingDataAdapter<ArticleOverview, RecyclerView.ViewHolder>(DiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) RecyclerType.HEADER.int else RecyclerType.BODY.int
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == RecyclerType.HEADER.int) {
            return TagRelatedArticleListHeaderViewHolder(
                TagRelatedArticleListHeaderViewBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
            )
        }
        return ArticleListItemAdapter.ArticleOverViewViewHolder(
            ArticleItemViewBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TagRelatedArticleListHeaderViewHolder -> {
                holder.bind(tag)
            }
            is ArticleListItemAdapter.ArticleOverViewViewHolder -> {
                var articleOverview = getItem(position)
                holder.bind(articleOverview, itemClickListener, tagclickListener)
            }
        }
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

    class TagRelatedArticleListHeaderViewHolder(private var binding: TagRelatedArticleListHeaderViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: ArticleOverview.Tag) {
            binding.property = tag
        }
    }
}
