package com.example.qiitaviewerandroid.api

import com.example.qiitaviewerandroid.model.ArticleDetail
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://qiita.com"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface ArticleDetailApiService {

    @GET("/api/v2/items/{item_id}")
    suspend fun fetchArticleDetail(
        @Path("item_id") itemID: Int
    ): ArticleDetail

    companion object {
        fun create(): ArticleDetailApiService {
            return retrofit.create(
                ArticleDetailApiService::class.java
            )
        }
    }
}
