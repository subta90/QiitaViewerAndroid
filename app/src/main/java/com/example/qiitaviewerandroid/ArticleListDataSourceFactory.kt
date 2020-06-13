package com.example.qiitaviewerandroid

import androidx.paging.DataSource

class ArticleListDataSourceFactory(service: ArticleListApiService, query: String?) :
    DataSource.Factory<Int, ArticleOverview>() {

    val source = ArticleListPagingSource(service, query)

    override fun create(): DataSource<Int, ArticleOverview> {
        return source
    }
}
