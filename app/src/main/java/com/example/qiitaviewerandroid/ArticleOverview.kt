package com.example.qiitaviewerandroid

import java.util.*

data class ArticleOverview(
    val id: Int,
    val title: String,
    val contributor: String,
    val postingDate: Date,
    val tags: List<String>
)