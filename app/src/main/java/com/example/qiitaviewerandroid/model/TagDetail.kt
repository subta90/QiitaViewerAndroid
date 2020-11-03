package com.example.qiitaviewerandroid.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import org.parceler.ParcelConstructor
import org.parceler.ParcelProperty

@JsonClass(generateAdapter = true)
@Parcelize
data class TagDetail @ParcelConstructor constructor(
    @Json(name = "followers_count") @ParcelProperty("followersCount") val followersCount: Int,
    @Json(name = "icon_url") @ParcelProperty("iconUrl") val iconURL: String,
    @ParcelProperty("id") val id: String,
    @Json(name = "items_count") @ParcelProperty("itemsCount") val itemsCount: Int
) : Parcelable
