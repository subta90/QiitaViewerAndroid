package com.example.qiitaviewerandroid.view.tagrelatedarticlelist

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.qiitaviewerandroid.api.TagRelatedArticleListApiService
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.model.TagDetail
import com.example.qiitaviewerandroid.repository.TagRelatedArticleListRepository
import kotlinx.coroutines.flow.Flow

class TagRelatedArticleListViewModel : ViewModel() {

    private var currentTagID: String? = null

    var currentArticleList: Flow<PagingData<ArticleOverview>>? = null

    // TODO: injection
    private val repository =
        TagRelatedArticleListRepository(service = TagRelatedArticleListApiService.create())

    suspend fun searchTagDetail(tagID: String): TagDetail {
        return repository.getSearchTagDetail(tagID)
    }

    fun searchTagRelatedArticleList(tagID: String): Flow<PagingData<ArticleOverview>> {
        val lastArticleList = currentArticleList
        if (tagID == currentTagID && lastArticleList != null) {
            return lastArticleList
        }

        currentTagID = tagID
        val newResult = repository.getSearchTagRelatedArticleListStream(tagID)
        currentArticleList = newResult
        return newResult
    }


}
