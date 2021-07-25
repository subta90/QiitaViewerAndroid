package com.example.qiitaviewerandroid.view.tagrelatedarticlelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.example.qiitaviewerandroid.api.TagRelatedArticleListApiService
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.model.TagDetail
import com.example.qiitaviewerandroid.repository.TagRelatedArticleListRepository
import kotlinx.coroutines.flow.Flow

class TagRelatedArticleListViewModel(tagID: String) : ViewModel() {

    // TODO: Daggerを入れる前の繋ぎ
    class Factory(private val tagID: String): ViewModelProvider.NewInstanceFactory() {
        override fun <T: ViewModel?> create(modelClass: Class<T>): T {
            return TagRelatedArticleListViewModel(tagID) as T
        }
    }

    private var currentArticleList: Flow<PagingData<ArticleOverview>>? = null

    // TODO: injection
    private val repository =
        TagRelatedArticleListRepository(service = TagRelatedArticleListApiService.create(),
        tagID = tagID)

    suspend fun searchTagDetail(): TagDetail {
        return repository.getSearchTagDetail()
    }

    fun searchTagRelatedArticleList(): Flow<PagingData<ArticleOverview>>  {
        val lastArticleList = currentArticleList
        if (lastArticleList != null) {
            return lastArticleList
        }

        val newResult = repository.tagRelatedArticleListStream
        currentArticleList = newResult
        return newResult
    }
}

