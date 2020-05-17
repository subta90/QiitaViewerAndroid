package com.example.qiitaviewerandroid

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
    companion object {
        fun dummyData(): List<ArticleOverview> {

            val data1 = ArticleOverview(
                id = "0",
                title = "hoge",
                user = User("山田太郎"),
                createdAt = "2020/05/10 12:00:00",
                tags = listOf(Tag("Kotlin"), Tag("Android"))
            )

            val data2 = ArticleOverview(
                id = "1",
                title = "fuga",
                user = User("田中一郎"),
                createdAt = "2020/05/09 12:00:00",
                tags = listOf(Tag("Swift"), Tag("iOS"))
            )

            val data3 = ArticleOverview(
                id = "2",
                title = "moge",
                user = User("山田花子"),
                createdAt = "2020/05/08 11:30:00",
                tags = listOf(Tag("C"), Tag("Win32"))
            )

            return listOf(data1, data2, data3)
        }
    }

    data class User(
        val name: String
    )

    data class Tag(
        val name: String
    )
}