package com.example.qiitaviewerandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagingData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

enum class ArticleListLoadingState {
    INITIALIZE,
    REFRESHING,
    COMPLETE,
    ERROR
}

class ArticleListViewModel : ViewModel() {

    private var currentQueryValue: String? = null

    var currentArticleList: Flow<PagingData<ArticleOverview>>? = null

    // TODO: injection
    private val repository = ArticleListRepository(service = ArticleListApiService.create())

    fun searchArticleList(queryString: String): Flow<PagingData<ArticleOverview>> {
        val lastArticleList = currentArticleList
        if (queryString == currentQueryValue && lastArticleList != null) {
            return lastArticleList
        }

        currentQueryValue = queryString
        val newResult: Flow<PagingData<ArticleOverview>> =
            repository.getSearchResultStream(queryString)
        currentArticleList = newResult
        return newResult
    }

}
