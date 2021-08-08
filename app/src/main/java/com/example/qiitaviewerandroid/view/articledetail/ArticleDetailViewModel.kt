package com.example.qiitaviewerandroid.view.articledetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.qiitaviewerandroid.api.ArticleDetailApiService
import com.example.qiitaviewerandroid.model.ArticleDetail
import com.example.qiitaviewerandroid.repository.ArticleDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(private val itemID: String): ViewModel() {

    // TODO: Daggerを入れる前の繋ぎ
    class Factory(private val itemID: String): ViewModelProvider.NewInstanceFactory() {
        override fun <T: ViewModel?> create(modelClass: Class<T>): T {
            return ArticleDetailViewModel(itemID) as T
        }
    }

    private val _articleDetailState: MutableStateFlow<LatestArticleDetailState?> = MutableStateFlow(null)
    val articleDetailState: StateFlow<LatestArticleDetailState?> = _articleDetailState

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

    fun toBody(): String? {
        return if (this is Success) this.articleDetail?.body else null
    }
}

