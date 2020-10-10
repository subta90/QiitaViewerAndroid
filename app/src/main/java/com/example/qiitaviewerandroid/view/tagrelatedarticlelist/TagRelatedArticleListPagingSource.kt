package com.example.qiitaviewerandroid.view.tagrelatedarticlelist

import androidx.paging.PagingSource
import com.example.qiitaviewerandroid.api.TagRelatedArticleListApiService
import com.example.qiitaviewerandroid.model.ArticleOverview
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class TagRelatedArticleListPagingSource(
    private val service: TagRelatedArticleListApiService,
    private val tagID: String
) : PagingSource<Int, ArticleOverview>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleOverview> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = service.fetchTagRelatedArticleList(
                page = position,
                perPage = params.loadSize,
                tagID = tagID
            )
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}

