package com.example.qiitaviewerandroid.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import org.parceler.ParcelConstructor
import org.parceler.ParcelProperty

@JsonClass(generateAdapter = true)
@Parcelize
data class ArticleOverview @ParcelConstructor constructor(
    @ParcelProperty("id") val id: String,
    @ParcelProperty("title") val title: String,
    @ParcelProperty("user") val user: User,
    @Json(name = "created_at") @ParcelProperty("createdAt") val createdAt: String,
    @ParcelProperty("tags") val tags: List<Tag>
) : Parcelable {

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class User @ParcelConstructor constructor(
        @ParcelProperty("id") val id: String
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Tag @ParcelConstructor constructor(
        @ParcelProperty("name") val name: String
    ) : Parcelable
}
