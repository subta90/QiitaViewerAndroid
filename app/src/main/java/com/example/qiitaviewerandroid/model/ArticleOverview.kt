package com.example.qiitaviewerandroid.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import org.parceler.ParcelProperty

@JsonClass(generateAdapter = true)
@Parcel
data class ArticleOverview @ParcelConstructor constructor(
    @ParcelProperty("id") val id: String,
    @ParcelProperty("title") val title: String,
    @ParcelProperty("user") val user: User,
    @Json(name = "created_at") @ParcelProperty("createdAt") val createdAt: String,
    @ParcelProperty("tags") val tags: List<Tag>
) {

    @JsonClass(generateAdapter = true)
    @Parcel
    data class User @ParcelConstructor constructor(
        @ParcelProperty("id") val id: String
    )

    @JsonClass(generateAdapter = true)
    @Parcel
    data class Tag @ParcelConstructor constructor(
        @ParcelProperty("name") val name: String
    )
}
