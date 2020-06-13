package com.example.qiitaviewerandroid

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

private const val BASE_URL = "https://qiita.com"

class ArticleListApiService {

    private val httpClient = OkHttpClient()
    private val adapter: JsonAdapter<List<ArticleOverview>> = Moshi.Builder().build()
        .adapter(Types.newParameterizedType(List::class.java, ArticleOverview::class.java))

    fun fetchArticleList(page: Int, perPage: Int, query: String?): List<ArticleOverview>? {
        val targetURL = "$BASE_URL/api/v2/items"

        val httpUrl = targetURL.toHttpUrlOrNull()?.newBuilder().also { it ->
            it?.addQueryParameter("page", page.toString())
            it?.addQueryParameter("per_page", perPage.toString())
            query?.let { query ->
                it?.addQueryParameter("query", query)
            }
        }?.build() ?: return null

        val request = Request.Builder().url(httpUrl).get().build()

        val response = httpClient.newCall(request).execute()

        val responseBody = response.body?.string() ?: return null

        return adapter.fromJson(responseBody)
    }
}
