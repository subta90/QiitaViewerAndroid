package com.example.qiitaviewerandroid

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class ArticleListRepository(private val service: ArticleListApiService) {

    fun getSearchResultStream(query: String): Flow<PagingData<ArticleOverview>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { ArticleListPagingSource(service, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

}
