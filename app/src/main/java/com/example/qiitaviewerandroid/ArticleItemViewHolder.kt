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
                val articleTextView = layoutInflater.inflate(R.layout.article_item_view, parent, false) as TextView
                return ArticleItemViewHolder(articleTextView)
            }
        }

        private val textView: TextView = view.findViewById(R.id.article_item_title)

        fun bind(item: ArticleOverview) {
            textView.text = item.title
        }

    }

}

