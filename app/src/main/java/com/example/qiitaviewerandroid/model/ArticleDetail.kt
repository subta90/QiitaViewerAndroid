package com.example.qiitaviewerandroid.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import org.parceler.ParcelConstructor
import org.parceler.ParcelProperty

@JsonClass(generateAdapter = true)
@Parcelize
data class ArticleDetail @ParcelConstructor constructor(
    @ParcelProperty("body") val body: String
) : Parcelable

