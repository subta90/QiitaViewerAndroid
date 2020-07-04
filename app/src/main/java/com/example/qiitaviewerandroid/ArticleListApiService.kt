package com.example.qiitaviewerandroid

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://qiita.com"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

interface ArticleListApiService {

    @GET("/api/v2/items")
    suspend fun fetchArticleList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("query") query: String? = null
    ): List<ArticleOverview>

    companion object {
        fun create(): ArticleListApiService {
            return retrofit.create(ArticleListApiService::class.java)
        }
    }
}
