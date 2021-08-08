package com.example.qiitaviewerandroid.view.articledetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qiitaviewerandroid.api.ArticleDetailApiService
import com.example.qiitaviewerandroid.model.ArticleDetail
import com.example.qiitaviewerandroid.repository.ArticleDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(private val itemID: String): ViewModel() {

    private val _articleDetailState = MutableStateFlow(LatestArticleDetailState.Success(null))
    val articleDetailState: StateFlow<LatestArticleDetailState> = _articleDetailState

    // TODO: injection
    private val repository = ArticleDetailRepository(service = ArticleDetailApiService.create())

    init {
        viewModelScope.launch {
            // TODO: Error Handling
            val articleDetail = repository.fetchArticleDetail(itemID)
            _articleDetailState.value = LatestArticleDetailState.Success(articleDetail)
        }
    }

}

sealed class LatestArticleDetailState {
    data class Success(val articleDetail: ArticleDetail?): LatestArticleDetailState()
    data class Error(val exception: Throwable): LatestArticleDetailState()
}
