package com.example.qiitaviewerandroid

import androidx.paging.PageKeyedDataSource

class ArticleListPagingSource(
    private val service: ArticleListApiService,
    private val query: String?
) : PageKeyedDataSource<Int, ArticleOverview>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticleOverview>
    ) {
        val page = 1

        val perPage = params.requestedLoadSize

        var items = service.fetchArticleList(page, perPage, query) ?: return

        var nextPage = page + 1

        callback.onResult(items, null, nextPage)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleOverview>) {
        val page = params.key

        val perPage = params.requestedLoadSize

        val items = service.fetchArticleList(page, perPage, query) ?: return

        val nextPage = page + 1
        callback.onResult(items, nextPage)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleOverview>) {
        TODO("Not yet implemented")
    }

}
