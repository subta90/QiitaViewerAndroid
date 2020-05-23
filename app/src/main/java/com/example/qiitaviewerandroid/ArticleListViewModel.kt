package com.example.qiitaviewerandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ArticleListViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _articleList = MutableLiveData<List<ArticleOverview>>()
    val articleList: LiveData<List<ArticleOverview>>
        get() = _articleList

    private val apiService = ArticleListApiService()

    private var currentPage = 1
    private val perPage = 10
    private var currentQuery: String? = null

    fun fetchArticleList() {
        coroutineScope.launch {
            withContext(Dispatchers.Default) {
                apiService.fetchArticleList(
                    page = currentPage,
                    perPage = perPage,
                    query = currentQuery
                )
            }.let {
                _articleList.value = it
            }
        }
    }
}
