package com.example.qiitaviewerandroid.view.articlelist

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.qiitaviewerandroid.repository.ArticleListRepository
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.api.ArticleListApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleListViewModel : ViewModel() {

    private var currentQueryValue: String? = null

    var currentArticleList: Flow<PagingData<ArticleOverview>>? = null

    // TODO: injection
    private val repository =
        ArticleListRepository(service = ArticleListApiService.create())

    fun searchArticleList(queryString: String?): Flow<PagingData<ArticleOverview>> {
        val lastArticleList = currentArticleList
        if (queryString == currentQueryValue && lastArticleList != null) {
            return lastArticleList
        }

        currentQueryValue = queryString
        val newResult: Flow<PagingData<ArticleOverview>> =
            repository.getSearchArticleListStream(queryString)
        currentArticleList = newResult
        return newResult
    }

}
