package com.example.qiitaviewerandroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.api.ArticleListApiService
import com.example.qiitaviewerandroid.view.ArticleListPagingSource
import kotlinx.coroutines.flow.Flow

class ArticleListRepository(private val service: ArticleListApiService) {

    fun getSearchResultStream(query: String? = null): Flow<PagingData<ArticleOverview>> {
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

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

}
