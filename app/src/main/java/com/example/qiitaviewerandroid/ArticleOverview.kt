package com.example.qiitaviewerandroid

import java.util.*

data class ArticleOverview(
    val id: Int,
    val title: String,
    val contributor: String,
    val postingDate: String,
    val tags: List<String>
) {
    companion object {
        fun dummyData(): List<ArticleOverview> {

            val data1 = ArticleOverview(
                id = 0,
                title = "hoge",
                contributor = "山田太郎",
                postingDate = "2020/05/10 12:00:00",
                tags = listOf("Kotlin", "Android")
            )

            val data2 = ArticleOverview(
                id = 1,
                title = "fuga",
                contributor = "田中一郎",
                postingDate = "2020/05/09 12:00:00",
                tags = listOf("Swift", "iOS")
            )

            val data3 = ArticleOverview(
                id = 2,
                title = "moge",
                contributor = "山田花子",
                postingDate = "2020/05/08 11:30:00",
                tags = listOf("C", "Win32")
            )

            return listOf(data1, data2, data3)
        }
    }
}