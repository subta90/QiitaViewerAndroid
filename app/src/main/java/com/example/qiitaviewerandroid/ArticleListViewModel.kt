package com.example.qiitaviewerandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.*

enum class ArticleListLoadingState {
    INITIALIZE,
    REFRESHING,
    COMPLETE,
    ERROR
}

class ArticleListViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()

    val articleList: LiveData<PagedList<ArticleOverview>> = LivePagedListBuilder(ArticleListDataSourceFactory(ArticleListApiService(), ""), config).build()

    private val _loadingState = MutableLiveData<ArticleListLoadingState>()
    val loadingState: LiveData<ArticleListLoadingState>
        get() = _loadingState

    private val apiService = ArticleListApiService()
    private var currentQuery: String? = null

    fun refresh() {
        _loadingState.value = ArticleListLoadingState.REFRESHING
    }

    private fun initialFetch() {
        _loadingState.value = ArticleListLoadingState.INITIALIZE
    }

}
