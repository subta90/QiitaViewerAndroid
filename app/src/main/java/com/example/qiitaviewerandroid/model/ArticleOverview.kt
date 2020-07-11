package com.example.qiitaviewerandroid.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleOverview(
    val id: String,
    val title: String,
    val user: User,
    @Json(name = "created_at") val createdAt: String,
    val tags: List<Tag>
) {

    @JsonClass(generateAdapter = true)
    data class User(
        val id: String
    )

    @JsonClass(generateAdapter = true)
    data class Tag(
        val name: String
    )
}
