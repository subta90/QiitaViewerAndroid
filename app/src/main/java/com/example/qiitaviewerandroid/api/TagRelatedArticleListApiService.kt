package com.example.qiitaviewerandroid.api

import com.example.qiitaviewerandroid.model.ArticleOverview
import com.example.qiitaviewerandroid.model.TagDetail
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://qiita.com"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

interface TagRelatedArticleListApiService {

    @GET("/api/v2/tags/{tagID}/items")
    suspend fun fetchTagRelatedArticleList(
        @Path("tagID") tagID: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<ArticleOverview>

    @GET("/api/v2/tags/{tagID}")
    suspend fun fetchTagDetail(
        @Path("tagID") tagID: String
    ): TagDetail

    companion object {
        fun create(): TagRelatedArticleListApiService {
            return retrofit.create(
                TagRelatedArticleListApiService::class.java
            )
        }
    }
}
