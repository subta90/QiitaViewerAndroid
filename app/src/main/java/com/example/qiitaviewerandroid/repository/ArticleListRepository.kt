package com.example.qiitaviewerandroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.api.ArticleListApiService
import com.example.qiitaviewerandroid.view.articlelist.ArticleListPagingSource
import com.example.qiitaviewerandroid.view.articlelist.TagRelatedArticleListPagingSource
import kotlinx.coroutines.flow.Flow

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

    fun getSearchTagRelatedArticleListStream(tagID: String): Flow<PagingData<ArticleOverview>> {
        return Pager(
            config = PagingConfig(pageSize =  NETWORK_PAGE_SIZE),
            pagingSourceFactory =  {
                TagRelatedArticleListPagingSource(
                    service,
                    tagID
                )
            }
        ).flow

    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

}
