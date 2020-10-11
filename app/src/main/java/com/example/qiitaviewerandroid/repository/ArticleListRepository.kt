package com.example.qiitaviewerandroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.api.ArticleListApiService
import com.example.qiitaviewerandroid.view.articlelist.ArticleListPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleListRepository(private val service: ArticleListApiService) {

    fun getSearchArticleListStream(query: String? = null): Flow<PagingData<ArticleOverview>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                ArticleListPagingSource(
                    service,
                    query
                )
            }
        ).flow
    }

    // TODO: 置き場所
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}
