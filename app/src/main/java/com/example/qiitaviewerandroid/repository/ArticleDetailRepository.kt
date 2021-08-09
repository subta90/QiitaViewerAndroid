package com.example.qiitaviewerandroid.repository

import com.example.qiitaviewerandroid.api.ArticleDetailApiService
import com.example.qiitaviewerandroid.model.ArticleDetail

class ArticleDetailRepository(private val service: ArticleDetailApiService) {

    suspend fun fetchArticleDetail(itemID: String): ArticleDetail {
        return service.fetchArticleDetail(itemID)
    }

}
