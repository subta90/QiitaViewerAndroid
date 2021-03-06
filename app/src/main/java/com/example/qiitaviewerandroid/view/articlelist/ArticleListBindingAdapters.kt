package com.example.qiitaviewerandroid.view.articlelist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.view.common.TagsAdapter

@BindingAdapter("tags")
fun bindTagsRecyclerView(recyclerView: RecyclerView, tags: List<ArticleOverview.Tag>?) {
    tags?.let {
        val adapter = recyclerView.adapter as TagsAdapter
        adapter.submitList(tags)
    }
}

@BindingAdapter("contributorText")
fun bindContributor(textView: TextView, contributorString: String?) {
    contributorString?.let {
        textView.text = contributorString
    }
}

@BindingAdapter("titleText")
fun bindTitle(textView: TextView, titleString: String?) {
    titleString?.let {
        textView.text = titleString
    }
}

@BindingAdapter("dateText")
fun bindDate(textView: TextView, dateString: String?) {
    dateString?.let {
        textView.text = dateString
    }
}

