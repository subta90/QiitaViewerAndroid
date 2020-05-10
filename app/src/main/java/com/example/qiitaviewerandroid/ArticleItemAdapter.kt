package com.example.qiitaviewerandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.ArticleItemViewHolder>() {

    var articles = listOf<ArticleOverview>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        return ArticleItemViewHolder.from(parent)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    class ArticleItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): ArticleItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val articleView = layoutInflater.inflate(R.layout.article_item_view, parent, false)
                return ArticleItemViewHolder(articleView)
            }
        }

        private val contributorView: TextView = view.findViewById(R.id.article_item_contributor)
        private val titleView: TextView = view.findViewById(R.id.article_item_title)
        private val dateView: TextView = view.findViewById(R.id.article_item_date)
        private val tagsView: RecyclerView = view.findViewById(R.id.article_item_tags)

        fun bind(item: ArticleOverview) {
            contributorView.text = item.contributor
            titleView.text = item.title
            dateView.text = item.postingDate

            val adapter = TagsAdapter()
            adapter.tags = item.tags
            tagsView.adapter = adapter
        }

    }

}

