package com.example.qiitaviewerandroid.view.articlelist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.api.ArticleListApiService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ArticleListPagingSource(
    private val service: ArticleListApiService,
    private val query: String? = null
) : PagingSource<Int, ArticleOverview>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleOverview> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response =
                service.fetchArticleList(page = position, perPage = params.loadSize, query = query)
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

    override fun getRefreshKey(state: PagingState<Int, ArticleOverview>): Int? {
        TODO("Not yet implemented")
    }
}
