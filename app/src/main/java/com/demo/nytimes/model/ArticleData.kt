package com.demo.nytimes.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleData(
    val status: String,
    val results: ArrayList<ArticleDetailsData>
) : Parcelable

@Parcelize
class ArticleDetailsData(
    val source: String = "",
    val published_date: String,
    val title: String,
    val adx_keywords: String,
    val abstract: String,
    val media: ArrayList<MediaData>
) : Parcelable

@Parcelize
class MediaData(
    @SerializedName("media-metadata")
    val mediaMetadata: ArrayList<ThumbnailData>,
) : Parcelable

@Parcelize
class ThumbnailData(
    val url: String = ""
) : Parcelable
