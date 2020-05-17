package com.example.qiitaviewerandroid

import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException

private const val BASE_URL = "https://qiita.com/api/v2/items"

class ArticleListApiService {

    private val httpClient = OkHttpClient()

    fun fetchArticleList(page: Int, perPage: Int, query: String) {
        val targetURL = "$BASE_URL/api/v2/items"

        val httpUrl = targetURL.toHttpUrlOrNull()?.newBuilder().also {
            it?.addQueryParameter("page", page.toString())
            it?.addQueryParameter("per_page", perPage.toString())
            it?.addQueryParameter("query", query)
            it
        }?.build() ?: return

        val request = Request.Builder().url(httpUrl).get().build()

        val response = httpClient.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                responseBody
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Main", "onFailure ${e.message}", e)
                TODO("implement error handling.")
            }
        })
    }
}