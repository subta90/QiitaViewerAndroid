package com.example.qiitaviewerandroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.qiitaviewerandroid.api.TagRelatedArticleListApiService
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.model.TagDetail
import com.example.qiitaviewerandroid.view.tagrelatedarticlelist.TagRelatedArticleListPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TagRelatedArticleListRepository(private val service: TagRelatedArticleListApiService) {

    suspend fun getSearchTagDetail(tagID: String): TagDetail {
        return withContext(Dispatchers.IO) {
            service.fetchTagDetail(tagID)
        }
    }

    fun getSearchTagRelatedArticleListStream(tagID: String): Flow<PagingData<ArticleOverview>> {
        return Pager(
            config = PagingConfig(pageSize = ArticleListRepository.NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                TagRelatedArticleListPagingSource(
                    service,
                    tagID
                )
            }
        ).flow
    }

}
