package com.example.qiitaviewerandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val _articleList = MutableLiveData<List<ArticleOverview>>()
    val articleList: LiveData<List<ArticleOverview>>
        get() = _articleList

    private val _loadingState = MutableLiveData<ArticleListLoadingState>()
    val loadingState: LiveData<ArticleListLoadingState>
        get() = _loadingState

    private val apiService = ArticleListApiService()

    private var currentPage = 0
    private val perPage = 10
    private var currentQuery: String? = null

    init {
        initialFetch()
    }

    fun refresh() {
        _loadingState.value = ArticleListLoadingState.REFRESHING
        currentPage = 0
        fetchArticleList()
    }

    private fun initialFetch() {
        _loadingState.value = ArticleListLoadingState.INITIALIZE
        fetchArticleList()
    }

    private fun fetchArticleList() {
        coroutineScope.launch {
            withContext(Dispatchers.Default) {
                apiService.fetchArticleList(
                    page = currentPage + 1,
                    perPage = perPage,
                    query = currentQuery
                )
            }.let {
                _articleList.value = it
                currentPage++
                _loadingState.value = ArticleListLoadingState.COMPLETE
            }
        }
    }
}
